package com.triptodo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triptodo.dto.HospedagemRequestDTO;
import com.triptodo.dto.HospedagemResponseDTO;
import com.triptodo.exception.BusinessException;
import com.triptodo.exception.ResourceNotFoundException;
import com.triptodo.model.Destino;
import com.triptodo.model.Hospedagem;
import com.triptodo.repository.DestinoRepository;
import com.triptodo.repository.HospedagemRepository;

@Service
public class HospedagemService {

    private final HospedagemRepository hospedagemRepository;
    private final DestinoRepository destinoRepository;

    public HospedagemService(HospedagemRepository hospedagemRepository, DestinoRepository destinoRepository) {
        this.hospedagemRepository = hospedagemRepository;
        this.destinoRepository = destinoRepository;
    }

    @Transactional
    public HospedagemResponseDTO criar(HospedagemRequestDTO dto) {
        Destino destino = buscarDestinoExistente(dto.getDestinoId());
        validarDatas(dto, destino);

        Hospedagem hospedagem = new Hospedagem();
        hospedagem.setNome(dto.getNome());
        hospedagem.setEndereco(dto.getEndereco());
        hospedagem.setCheckIn(dto.getCheckIn());
        hospedagem.setCheckOut(dto.getCheckOut());
        hospedagem.setObservacao(dto.getObservacao());
        hospedagem.setDestino(destino);

        return new HospedagemResponseDTO(hospedagemRepository.save(hospedagem));
    }

    @Transactional(readOnly = true)
    public List<HospedagemResponseDTO> listarTodas() {
        return hospedagemRepository.findAll()
                .stream()
                .map(HospedagemResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<HospedagemResponseDTO> listarPorDestino(Long destinoId) {
        buscarDestinoExistente(destinoId);

        return hospedagemRepository.findByDestinoId(destinoId)
                .stream()
                .map(HospedagemResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public HospedagemResponseDTO buscarPorId(Long id) {
        return new HospedagemResponseDTO(buscarHospedagemExistente(id));
    }

    @Transactional
    public HospedagemResponseDTO atualizar(Long id, HospedagemRequestDTO dto) {
        Hospedagem hospedagem = buscarHospedagemExistente(id);
        Destino destino = buscarDestinoExistente(dto.getDestinoId());

        validarDatas(dto, destino);

        hospedagem.setNome(dto.getNome());
        hospedagem.setEndereco(dto.getEndereco());
        hospedagem.setCheckIn(dto.getCheckIn());
        hospedagem.setCheckOut(dto.getCheckOut());
        hospedagem.setObservacao(dto.getObservacao());
        hospedagem.setDestino(destino);

        return new HospedagemResponseDTO(hospedagemRepository.save(hospedagem));
    }

    @Transactional
    public void deletar(Long id) {
        hospedagemRepository.delete(buscarHospedagemExistente(id));
    }

    private Hospedagem buscarHospedagemExistente(Long id) {
        return hospedagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospedagem não encontrada com ID: " + id));
    }

    private Destino buscarDestinoExistente(Long id) {
        return destinoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destino não encontrado com ID: " + id));
    }

    private void validarDatas(HospedagemRequestDTO dto, Destino destino) {
        if (dto.getCheckOut().isBefore(dto.getCheckIn())) {
            throw new BusinessException("O check-out não pode ser menor que o check-in.");
        }

        if (destino.getDataChegada() != null && dto.getCheckIn().isBefore(destino.getDataChegada())) {
            throw new BusinessException("O check-in não pode ser menor que a data de chegada do destino.");
        }

        if (destino.getDataSaida() != null && dto.getCheckOut().isAfter(destino.getDataSaida())) {
            throw new BusinessException("O check-out não pode ser maior que a data de saída do destino.");
        }
    }
}