package com.triptodo.dto;

import java.time.LocalDateTime;

import com.triptodo.model.Transfer;

public class TransferResponseDTO {

    private Long id;
    private String tipo;
    private String localOrigem;
    private String localDestino;
    private LocalDateTime dataHora;
    private String observacao;
    private Long destinoId;

    public TransferResponseDTO() {
    }

    public TransferResponseDTO(Long id, String tipo, String localOrigem, String localDestino,
                               LocalDateTime dataHora, String observacao, Long destinoId) {
        this.id = id;
        this.tipo = tipo;
        this.localOrigem = localOrigem;
        this.localDestino = localDestino;
        this.dataHora = dataHora;
        this.observacao = observacao;
        this.destinoId = destinoId;
    }

    public TransferResponseDTO(Transfer transfer) {
        this.id = transfer.getId();
        this.tipo = transfer.getTipo();
        this.localOrigem = transfer.getLocalOrigem();
        this.localDestino = transfer.getLocalDestino();
        this.dataHora = transfer.getDataHora();
        this.observacao = transfer.getObservacao();
        this.destinoId = transfer.getDestino() != null ? transfer.getDestino().getId() : null;
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getLocalOrigem() {
        return localOrigem;
    }

    public String getLocalDestino() {
        return localDestino;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getObservacao() {
        return observacao;
    }

    public Long getDestinoId() {
        return destinoId;
    }
}