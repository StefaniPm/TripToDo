package com.triptodo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triptodo.dto.ViagemRequestDTO;
import com.triptodo.dto.ViagemResponseDTO;
import com.triptodo.exception.BusinessException;
import com.triptodo.exception.ResourceNotFoundException;
import com.triptodo.model.Usuario;
import com.triptodo.model.Viagem;
import com.triptodo.repository.UsuarioRepository;
import com.triptodo.repository.ViagemRepository;

@Service
public class ViagemService {

    private final ViagemRepository viagemRepository;
    private final UsuarioRepository usuarioRepository;

    public ViagemService(ViagemRepository viagemRepository, UsuarioRepository usuarioRepository) {
        this.viagemRepository = viagemRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public ViagemResponseDTO criar(ViagemRequestDTO dto) {
        validarDatas(dto);

        Usuario usuario = buscarUsuarioExistente(dto.getUsuarioId());

        Viagem viagem = new Viagem();
        viagem.setNome(dto.getNome());
        viagem.setDescricao(dto.getDescricao());
        viagem.setDataInicio(dto.getDataInicio());
        viagem.setDataFim(dto.getDataFim());
        viagem.setUsuario(usuario);

        Viagem viagemSalva = viagemRepository.save(viagem);

        return new ViagemResponseDTO(viagemSalva);
    }

    @Transactional(readOnly = true)
    public List<ViagemResponseDTO> listarPorUsuario(Long usuarioId) {
        buscarUsuarioExistente(usuarioId);

        return viagemRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(ViagemResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ViagemResponseDTO buscarPorId(Long id, Long usuarioId) {
        Viagem viagem = buscarViagemDoUsuario(id, usuarioId);

        return new ViagemResponseDTO(viagem);
    }

    @Transactional
    public ViagemResponseDTO atualizar(Long id, Long usuarioId, ViagemRequestDTO dto) {
        validarDatas(dto);

        Viagem viagem = buscarViagemDoUsuario(id, usuarioId);

        viagem.setNome(dto.getNome());
        viagem.setDescricao(dto.getDescricao());
        viagem.setDataInicio(dto.getDataInicio());
        viagem.setDataFim(dto.getDataFim());

        Viagem viagemAtualizada = viagemRepository.save(viagem);

        return new ViagemResponseDTO(viagemAtualizada);
    }

    @Transactional
    public void deletar(Long id, Long usuarioId) {
        Viagem viagem = buscarViagemDoUsuario(id, usuarioId);

        viagemRepository.delete(viagem);
    }

    private Viagem buscarViagemDoUsuario(Long id, Long usuarioId) {
        return viagemRepository.findByIdAndUsuarioId(id, usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Viagem não encontrada para este usuário."));
    }

    private Usuario buscarUsuarioExistente(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + usuarioId));
    }

    private void validarDatas(ViagemRequestDTO dto) {
        if (dto.getDataInicio() != null && dto.getDataFim() != null
                && dto.getDataFim().isBefore(dto.getDataInicio())) {
            throw new BusinessException("A data de fim não pode ser menor que a data de início.");
        }
    }
}