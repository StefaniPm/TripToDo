package com.triptodo.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TransferRequestDTO {

    @NotBlank(message = "O tipo do transfer é obrigatório.")
    private String tipo;

    @NotBlank(message = "O local de origem é obrigatório.")
    private String localOrigem;

    @NotBlank(message = "O local de destino é obrigatório.")
    private String localDestino;

    @NotNull(message = "A data e hora do transfer são obrigatórias.")
    private LocalDateTime dataHora;

    private String observacao;

    @NotNull(message = "O ID do destino é obrigatório.")
    private Long destinoId;

    public TransferRequestDTO() {
    }

    public TransferRequestDTO(String tipo, String localOrigem, String localDestino,
                              LocalDateTime dataHora, String observacao, Long destinoId) {
        this.tipo = tipo;
        this.localOrigem = localOrigem;
        this.localDestino = localDestino;
        this.dataHora = dataHora;
        this.observacao = observacao;
        this.destinoId = destinoId;
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

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setLocalOrigem(String localOrigem) {
        this.localOrigem = localOrigem;
    }

    public void setLocalDestino(String localDestino) {
        this.localDestino = localDestino;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setDestinoId(Long destinoId) {
        this.destinoId = destinoId;
    }
}