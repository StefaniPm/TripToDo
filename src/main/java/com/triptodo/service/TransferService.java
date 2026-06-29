package com.triptodo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triptodo.dto.TransferRequestDTO;
import com.triptodo.dto.TransferResponseDTO;
import com.triptodo.exception.BusinessException;
import com.triptodo.exception.ResourceNotFoundException;
import com.triptodo.model.Destino;
import com.triptodo.model.Transfer;
import com.triptodo.repository.DestinoRepository;
import com.triptodo.repository.TransferRepository;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final DestinoRepository destinoRepository;

    public TransferService(TransferRepository transferRepository, DestinoRepository destinoRepository) {
        this.transferRepository = transferRepository;
        this.destinoRepository = destinoRepository;
    }

    @Transactional
    public TransferResponseDTO criar(TransferRequestDTO dto) {
        Destino destino = buscarDestinoExistente(dto.getDestinoId());
        validarData(dto, destino);

        Transfer transfer = new Transfer();
        transfer.setTipo(dto.getTipo());
        transfer.setLocalOrigem(dto.getLocalOrigem());
        transfer.setLocalDestino(dto.getLocalDestino());
        transfer.setDataHora(dto.getDataHora());
        transfer.setObservacao(dto.getObservacao());
        transfer.setDestino(destino);

        return new TransferResponseDTO(transferRepository.save(transfer));
    }

    @Transactional(readOnly = true)
    public List<TransferResponseDTO> listarTodos() {
        return transferRepository.findAll()
                .stream()
                .map(TransferResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TransferResponseDTO> listarPorDestino(Long destinoId) {
        buscarDestinoExistente(destinoId);

        return transferRepository.findByDestinoId(destinoId)
                .stream()
                .map(TransferResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public TransferResponseDTO buscarPorId(Long id) {
        return new TransferResponseDTO(buscarTransferExistente(id));
    }

    @Transactional
    public TransferResponseDTO atualizar(Long id, TransferRequestDTO dto) {
        Transfer transfer = buscarTransferExistente(id);
        Destino destino = buscarDestinoExistente(dto.getDestinoId());

        validarData(dto, destino);

        transfer.setTipo(dto.getTipo());
        transfer.setLocalOrigem(dto.getLocalOrigem());
        transfer.setLocalDestino(dto.getLocalDestino());
        transfer.setDataHora(dto.getDataHora());
        transfer.setObservacao(dto.getObservacao());
        transfer.setDestino(destino);

        return new TransferResponseDTO(transferRepository.save(transfer));
    }

    @Transactional
    public void deletar(Long id) {
        transferRepository.delete(buscarTransferExistente(id));
    }

    private Transfer buscarTransferExistente(Long id) {
        return transferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer não encontrado com ID: " + id));
    }

    private Destino buscarDestinoExistente(Long id) {
        return destinoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destino não encontrado com ID: " + id));
    }

    private void validarData(TransferRequestDTO dto, Destino destino) {
        if (destino.getDataChegada() != null &&
                dto.getDataHora().toLocalDate().isBefore(destino.getDataChegada())) {
            throw new BusinessException("A data do transfer não pode ser menor que a data de chegada do destino.");
        }

        if (destino.getDataSaida() != null &&
                dto.getDataHora().toLocalDate().isAfter(destino.getDataSaida())) {
            throw new BusinessException("A data do transfer não pode ser maior que a data de saída do destino.");
        }
    }
}