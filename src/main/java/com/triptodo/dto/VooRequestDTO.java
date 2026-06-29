package com.triptodo.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VooRequestDTO {

    @NotBlank(message = "A companhia é obrigatória.")
    private String companhia;

    @NotBlank(message = "O número do voo é obrigatório.")
    private String numeroVoo;

    @NotBlank(message = "O aeroporto de origem é obrigatório.")
    private String aeroportoOrigem;

    @NotBlank(message = "O aeroporto de destino é obrigatório.")
    private String aeroportoDestino;

    @NotNull(message = "A data e hora de partida são obrigatórias.")
    private LocalDateTime dataHoraPartida;

    @NotNull(message = "A data e hora de chegada são obrigatórias.")
    private LocalDateTime dataHoraChegada;

    @NotNull(message = "O ID do destino é obrigatório.")
    private Long destinoId;

    public VooRequestDTO() {
    }

    public VooRequestDTO(String companhia, String numeroVoo, String aeroportoOrigem,
                         String aeroportoDestino, LocalDateTime dataHoraPartida,
                         LocalDateTime dataHoraChegada, Long destinoId) {
        this.companhia = companhia;
        this.numeroVoo = numeroVoo;
        this.aeroportoOrigem = aeroportoOrigem;
        this.aeroportoDestino = aeroportoDestino;
        this.dataHoraPartida = dataHoraPartida;
        this.dataHoraChegada = dataHoraChegada;
        this.destinoId = destinoId;
    }

    public String getCompanhia() {
        return companhia;
    }

    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    }

    public String getNumeroVoo() {
        return numeroVoo;
    }

    public void setNumeroVoo(String numeroVoo) {
        this.numeroVoo = numeroVoo;
    }

    public String getAeroportoOrigem() {
        return aeroportoOrigem;
    }

    public void setAeroportoOrigem(String aeroportoOrigem) {
        this.aeroportoOrigem = aeroportoOrigem;
    }

    public String getAeroportoDestino() {
        return aeroportoDestino;
    }

    public void setAeroportoDestino(String aeroportoDestino) {
        this.aeroportoDestino = aeroportoDestino;
    }

    public LocalDateTime getDataHoraPartida() {
        return dataHoraPartida;
    }

    public void setDataHoraPartida(LocalDateTime dataHoraPartida) {
        this.dataHoraPartida = dataHoraPartida;
    }

    public LocalDateTime getDataHoraChegada() {
        return dataHoraChegada;
    }

    public void setDataHoraChegada(LocalDateTime dataHoraChegada) {
        this.dataHoraChegada = dataHoraChegada;
    }

    public Long getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(Long destinoId) {
        this.destinoId = destinoId;
    }
}