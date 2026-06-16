package com.triptodo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.triptodo.dto.ViagemRequestDTO;
import com.triptodo.dto.ViagemResponseDTO;
import com.triptodo.exception.BusinessException;
import com.triptodo.exception.ResourceNotFoundException;
import com.triptodo.model.Viagem;
import com.triptodo.repository.ViagemRepository;

@Service
public class ViagemService {

    private final ViagemRepository viagemRepository;

    public ViagemService(ViagemRepository viagemRepository) {
        this.viagemRepository = viagemRepository;
    }

    public ViagemResponseDTO criar(ViagemRequestDTO dto) {
        validarDatas(dto);

        Viagem viagem = new Viagem();
        viagem.setNome(dto.getNome());
        viagem.setDescricao(dto.getDescricao());
        viagem.setDataInicio(dto.getDataInicio());
        viagem.setDataFim(dto.getDataFim());

        Viagem viagemSalva = viagemRepository.save(viagem);
        return new ViagemResponseDTO(viagemSalva);
    }

    public List<ViagemResponseDTO> listarTodas() {
        return viagemRepository.findAll()
                .stream()
                .map(ViagemResponseDTO::new)
                .toList();
    }

    public ViagemResponseDTO buscarPorId(Long id) {
        Viagem viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viagem não encontrada com ID: " + id));

        return new ViagemResponseDTO(viagem);
    }

    public ViagemResponseDTO atualizar(Long id, ViagemRequestDTO dto) {
        validarDatas(dto);

        Viagem viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viagem não encontrada com ID: " + id));

        viagem.setNome(dto.getNome());
        viagem.setDescricao(dto.getDescricao());
        viagem.setDataInicio(dto.getDataInicio());
        viagem.setDataFim(dto.getDataFim());

        Viagem viagemAtualizada = viagemRepository.save(viagem);
        return new ViagemResponseDTO(viagemAtualizada);
    }

    public void deletar(Long id) {
        Viagem viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viagem não encontrada com ID: " + id));

        viagemRepository.delete(viagem);
    }

    private void validarDatas(ViagemRequestDTO dto) {
        if (dto.getDataInicio() != null && dto.getDataFim() != null
                && dto.getDataFim().isBefore(dto.getDataInicio())) {
            throw new BusinessException("A data de fim não pode ser menor que a data de início.");
        }
    }
}