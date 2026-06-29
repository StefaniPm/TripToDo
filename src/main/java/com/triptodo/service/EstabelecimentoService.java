package com.triptodo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triptodo.dto.EstabelecimentoRequestDTO;
import com.triptodo.dto.EstabelecimentoResponseDTO;
import com.triptodo.exception.ResourceNotFoundException;
import com.triptodo.model.Destino;
import com.triptodo.model.Estabelecimento;
import com.triptodo.model.TipoEstabelecimento;
import com.triptodo.repository.DestinoRepository;
import com.triptodo.repository.EstabelecimentoRepository;

@Service
public class EstabelecimentoService {

    private final EstabelecimentoRepository estabelecimentoRepository;
    private final DestinoRepository destinoRepository;

    public EstabelecimentoService(EstabelecimentoRepository estabelecimentoRepository,
                                  DestinoRepository destinoRepository) {
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.destinoRepository = destinoRepository;
    }

    @Transactional
    public EstabelecimentoResponseDTO criar(EstabelecimentoRequestDTO dto) {
        Destino destino = buscarDestinoExistente(dto.getDestinoId());

        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setNome(dto.getNome());
        estabelecimento.setEndereco(dto.getEndereco());
        estabelecimento.setObservacao(dto.getObservacao());
        estabelecimento.setTipo(dto.getTipo());
        estabelecimento.setDestino(destino);

        return new EstabelecimentoResponseDTO(estabelecimentoRepository.save(estabelecimento));
    }

    @Transactional(readOnly = true)
    public List<EstabelecimentoResponseDTO> listarTodos() {
        return estabelecimentoRepository.findAll()
                .stream()
                .map(EstabelecimentoResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public EstabelecimentoResponseDTO buscarPorId(Long id) {
        return new EstabelecimentoResponseDTO(buscarEstabelecimentoExistente(id));
    }

    @Transactional(readOnly = true)
    public List<EstabelecimentoResponseDTO> listarPorDestino(Long destinoId) {
        buscarDestinoExistente(destinoId);

        return estabelecimentoRepository.findByDestinoId(destinoId)
                .stream()
                .map(EstabelecimentoResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<EstabelecimentoResponseDTO> listarPorTipo(TipoEstabelecimento tipo) {
        return estabelecimentoRepository.findByTipo(tipo)
                .stream()
                .map(EstabelecimentoResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<EstabelecimentoResponseDTO> listarPorDestinoETipo(Long destinoId, TipoEstabelecimento tipo) {
        buscarDestinoExistente(destinoId);

        return estabelecimentoRepository.findByDestinoIdAndTipo(destinoId, tipo)
                .stream()
                .map(EstabelecimentoResponseDTO::new)
                .toList();
    }

    @Transactional
    public EstabelecimentoResponseDTO atualizar(Long id, EstabelecimentoRequestDTO dto) {
        Estabelecimento estabelecimento = buscarEstabelecimentoExistente(id);
        Destino destino = buscarDestinoExistente(dto.getDestinoId());

        estabelecimento.setNome(dto.getNome());
        estabelecimento.setEndereco(dto.getEndereco());
        estabelecimento.setObservacao(dto.getObservacao());
        estabelecimento.setTipo(dto.getTipo());
        estabelecimento.setDestino(destino);

        return new EstabelecimentoResponseDTO(estabelecimentoRepository.save(estabelecimento));
    }

    @Transactional
    public void deletar(Long id) {
        estabelecimentoRepository.delete(buscarEstabelecimentoExistente(id));
    }

    private Estabelecimento buscarEstabelecimentoExistente(Long id) {
        return estabelecimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estabelecimento não encontrado com ID: " + id));
    }

    private Destino buscarDestinoExistente(Long id) {
        return destinoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destino não encontrado com ID: " + id));
    }
}