package com.triptodo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triptodo.dto.VooRequestDTO;
import com.triptodo.dto.VooResponseDTO;
import com.triptodo.exception.BusinessException;
import com.triptodo.exception.ResourceNotFoundException;
import com.triptodo.model.Destino;
import com.triptodo.model.Voo;
import com.triptodo.repository.DestinoRepository;
import com.triptodo.repository.VooRepository;

@Service
public class VooService {

    private final VooRepository vooRepository;
    private final DestinoRepository destinoRepository;

    public VooService(VooRepository vooRepository, DestinoRepository destinoRepository) {
        this.vooRepository = vooRepository;
        this.destinoRepository = destinoRepository;
    }

    @Transactional
    public VooResponseDTO criar(VooRequestDTO dto) {
        Destino destino = buscarDestinoExistente(dto.getDestinoId());
        validarDatas(dto, destino);

        Voo voo = new Voo();
        voo.setCompanhia(dto.getCompanhia());
        voo.setNumeroVoo(dto.getNumeroVoo());
        voo.setAeroportoOrigem(dto.getAeroportoOrigem());
        voo.setAeroportoDestino(dto.getAeroportoDestino());
        voo.setDataHoraPartida(dto.getDataHoraPartida());
        voo.setDataHoraChegada(dto.getDataHoraChegada());
        voo.setDestino(destino);

        return new VooResponseDTO(vooRepository.save(voo));
    }

    @Transactional(readOnly = true)
    public List<VooResponseDTO> listarTodos() {
        return vooRepository.findAll()
                .stream()
                .map(VooResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<VooResponseDTO> listarPorDestino(Long destinoId) {
        buscarDestinoExistente(destinoId);

        return vooRepository.findByDestinoId(destinoId)
                .stream()
                .map(VooResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public VooResponseDTO buscarPorId(Long id) {
        return new VooResponseDTO(buscarVooExistente(id));
    }

    @Transactional
    public VooResponseDTO atualizar(Long id, VooRequestDTO dto) {
        Voo voo = buscarVooExistente(id);
        Destino destino = buscarDestinoExistente(dto.getDestinoId());

        validarDatas(dto, destino);

        voo.setCompanhia(dto.getCompanhia());
        voo.setNumeroVoo(dto.getNumeroVoo());
        voo.setAeroportoOrigem(dto.getAeroportoOrigem());
        voo.setAeroportoDestino(dto.getAeroportoDestino());
        voo.setDataHoraPartida(dto.getDataHoraPartida());
        voo.setDataHoraChegada(dto.getDataHoraChegada());
        voo.setDestino(destino);

        return new VooResponseDTO(vooRepository.save(voo));
    }

    @Transactional
    public void deletar(Long id) {
        vooRepository.delete(buscarVooExistente(id));
    }

    private Voo buscarVooExistente(Long id) {
        return vooRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voo não encontrado com ID: " + id));
    }

    private Destino buscarDestinoExistente(Long id) {
        return destinoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destino não encontrado com ID: " + id));
    }

    private void validarDatas(VooRequestDTO dto, Destino destino) {
        if (dto.getDataHoraChegada().isBefore(dto.getDataHoraPartida())) {
            throw new BusinessException("A data/hora de chegada não pode ser menor que a data/hora de partida.");
        }

        if (destino.getDataChegada() != null &&
                dto.getDataHoraPartida().toLocalDate().isBefore(destino.getDataChegada())) {
            throw new BusinessException("A partida do voo não pode ser antes da data de chegada do destino.");
        }

        if (destino.getDataSaida() != null &&
                dto.getDataHoraChegada().toLocalDate().isAfter(destino.getDataSaida())) {
            throw new BusinessException("A chegada do voo não pode ser depois da data de saída do destino.");
        }
    }
}