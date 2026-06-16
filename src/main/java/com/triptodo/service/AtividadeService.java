package com.triptodo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triptodo.dto.AtividadeRequestDTO;
import com.triptodo.dto.AtividadeResponseDTO;
import com.triptodo.exception.BusinessException;
import com.triptodo.exception.ResourceNotFoundException;
import com.triptodo.model.Atividade;
import com.triptodo.model.Destino;
import com.triptodo.repository.AtividadeRepository;
import com.triptodo.repository.DestinoRepository;

@Service
public class AtividadeService {

    private final AtividadeRepository atividadeRepository;
    private final DestinoRepository destinoRepository;

    public AtividadeService(AtividadeRepository atividadeRepository, DestinoRepository destinoRepository) {
        this.atividadeRepository = atividadeRepository;
        this.destinoRepository = destinoRepository;
    }

    @Transactional
    public AtividadeResponseDTO criar(AtividadeRequestDTO dto) {
        Destino destino = buscarDestinoExistente(dto.getDestinoId());
        validarData(dto, destino);

        Atividade atividade = new Atividade();
        atividade.setTitulo(dto.getTitulo());
        atividade.setDescricao(dto.getDescricao());
        atividade.setData(dto.getData());
        atividade.setHorario(dto.getHorario());
        atividade.setLocal(dto.getLocal());
        atividade.setDestino(destino);

        Atividade atividadeSalva = atividadeRepository.save(atividade);
        return new AtividadeResponseDTO(atividadeSalva);
    }

    @Transactional(readOnly = true)
    public List<AtividadeResponseDTO> listarTodas() {
        return atividadeRepository.findAll()
                .stream()
                .map(AtividadeResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<AtividadeResponseDTO> listarPorDestino(Long destinoId) {
        buscarDestinoExistente(destinoId);

        return atividadeRepository.findByDestinoIdOrderByDataAscHorarioAsc(destinoId)
                .stream()
                .map(AtividadeResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public AtividadeResponseDTO buscarPorId(Long id) {
        Atividade atividade = buscarAtividadeExistente(id);
        return new AtividadeResponseDTO(atividade);
    }

    @Transactional
    public AtividadeResponseDTO atualizar(Long id, AtividadeRequestDTO dto) {
        Atividade atividade = buscarAtividadeExistente(id);
        Destino destino = buscarDestinoExistente(dto.getDestinoId());

        validarData(dto, destino);

        atividade.setTitulo(dto.getTitulo());
        atividade.setDescricao(dto.getDescricao());
        atividade.setData(dto.getData());
        atividade.setHorario(dto.getHorario());
        atividade.setLocal(dto.getLocal());
        atividade.setDestino(destino);

        Atividade atividadeAtualizada = atividadeRepository.save(atividade);
        return new AtividadeResponseDTO(atividadeAtualizada);
    }

    @Transactional
    public void deletar(Long id) {
        Atividade atividade = buscarAtividadeExistente(id);
        atividadeRepository.delete(atividade);
    }

    private Atividade buscarAtividadeExistente(Long id) {
        return atividadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atividade não encontrada com ID: " + id));
    }

    private Destino buscarDestinoExistente(Long id) {
        return destinoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destino não encontrado com ID: " + id));
    }

    private void validarData(AtividadeRequestDTO dto, Destino destino) {
        if (dto.getData() == null) {
            throw new BusinessException("A data da atividade é obrigatória.");
        }

        if (destino.getDataChegada() != null && dto.getData().isBefore(destino.getDataChegada())) {
            throw new BusinessException("A data da atividade não pode ser menor que a data de chegada do destino.");
        }

        if (destino.getDataSaida() != null && dto.getData().isAfter(destino.getDataSaida())) {
            throw new BusinessException("A data da atividade não pode ser maior que a data de saída do destino.");
        }
    }
}