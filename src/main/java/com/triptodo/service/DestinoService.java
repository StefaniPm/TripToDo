package com.triptodo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triptodo.dto.DestinoRequestDTO;
import com.triptodo.dto.DestinoResponseDTO;
import com.triptodo.exception.BusinessException;
import com.triptodo.exception.ResourceNotFoundException;
import com.triptodo.model.Destino;
import com.triptodo.model.Viagem;
import com.triptodo.repository.DestinoRepository;
import com.triptodo.repository.ViagemRepository;

@Service
public class DestinoService {

    private final DestinoRepository destinoRepository;
    private final ViagemRepository viagemRepository;

    public DestinoService(DestinoRepository destinoRepository, ViagemRepository viagemRepository) {
        this.destinoRepository = destinoRepository;
        this.viagemRepository = viagemRepository;
    }

    @Transactional
    public DestinoResponseDTO criar(DestinoRequestDTO dto) {
        Viagem viagem = buscarViagemExistente(dto.getViagemId());
        validarDatas(dto, viagem);

        Destino destino = new Destino();
        destino.setNome(dto.getNome());
        destino.setCidade(dto.getCidade());
        destino.setPais(dto.getPais());
        destino.setDataChegada(dto.getDataChegada());
        destino.setDataSaida(dto.getDataSaida());
        destino.setViagem(viagem);

        Destino destinoSalvo = destinoRepository.save(destino);
        return new DestinoResponseDTO(destinoSalvo);
    }

    @Transactional(readOnly = true)
    public List<DestinoResponseDTO> listarTodos() {
        return destinoRepository.findAll()
                .stream()
                .map(DestinoResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<DestinoResponseDTO> listarPorViagem(Long viagemId) {
        buscarViagemExistente(viagemId);

        return destinoRepository.findByViagemId(viagemId)
                .stream()
                .map(DestinoResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public DestinoResponseDTO buscarPorId(Long id) {
        Destino destino = buscarDestinoExistente(id);
        return new DestinoResponseDTO(destino);
    }

    @Transactional
    public DestinoResponseDTO atualizar(Long id, DestinoRequestDTO dto) {
        Destino destino = buscarDestinoExistente(id);
        Viagem viagem = buscarViagemExistente(dto.getViagemId());

        validarDatas(dto, viagem);

        destino.setNome(dto.getNome());
        destino.setCidade(dto.getCidade());
        destino.setPais(dto.getPais());
        destino.setDataChegada(dto.getDataChegada());
        destino.setDataSaida(dto.getDataSaida());
        destino.setViagem(viagem);

        Destino destinoAtualizado = destinoRepository.save(destino);
        return new DestinoResponseDTO(destinoAtualizado);
    }

    @Transactional
    public void deletar(Long id) {
        Destino destino = buscarDestinoExistente(id);
        destinoRepository.delete(destino);
    }

    private Destino buscarDestinoExistente(Long id) {
        return destinoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destino não encontrado com ID: " + id));
    }

    private Viagem buscarViagemExistente(Long viagemId) {
        return viagemRepository.findById(viagemId)
                .orElseThrow(() -> new ResourceNotFoundException("Viagem não encontrada com ID: " + viagemId));
    }

    private void validarDatas(DestinoRequestDTO dto, Viagem viagem) {
        if (dto.getDataChegada() != null && dto.getDataSaida() != null
                && dto.getDataSaida().isBefore(dto.getDataChegada())) {
            throw new BusinessException("A data de saída não pode ser menor que a data de chegada.");
        }

        if (dto.getDataChegada() != null && viagem.getDataInicio() != null
                && dto.getDataChegada().isBefore(viagem.getDataInicio())) {
            throw new BusinessException("A data de chegada do destino não pode ser menor que a data de início da viagem.");
        }

        if (dto.getDataSaida() != null && viagem.getDataFim() != null
                && dto.getDataSaida().isAfter(viagem.getDataFim())) {
            throw new BusinessException("A data de saída do destino não pode ser maior que a data de fim da viagem.");
        }
    }
}