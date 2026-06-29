package com.triptodo.dto;

import java.time.LocalDateTime;

import com.triptodo.model.Voo;

public class VooResponseDTO {

    private Long id;
    private String companhia;
    private String numeroVoo;
    private String aeroportoOrigem;
    private String aeroportoDestino;
    private LocalDateTime dataHoraPartida;
    private LocalDateTime dataHoraChegada;
    private Long destinoId;

    public VooResponseDTO() {
    }

    public VooResponseDTO(Long id, String companhia, String numeroVoo,
                          String aeroportoOrigem, String aeroportoDestino,
                          LocalDateTime dataHoraPartida, LocalDateTime dataHoraChegada,
                          Long destinoId) {
        this.id = id;
        this.companhia = companhia;
        this.numeroVoo = numeroVoo;
        this.aeroportoOrigem = aeroportoOrigem;
        this.aeroportoDestino = aeroportoDestino;
        this.dataHoraPartida = dataHoraPartida;
        this.dataHoraChegada = dataHoraChegada;
        this.destinoId = destinoId;
    }

    public VooResponseDTO(Voo voo) {
        this.id = voo.getId();
        this.companhia = voo.getCompanhia();
        this.numeroVoo = voo.getNumeroVoo();
        this.aeroportoOrigem = voo.getAeroportoOrigem();
        this.aeroportoDestino = voo.getAeroportoDestino();
        this.dataHoraPartida = voo.getDataHoraPartida();
        this.dataHoraChegada = voo.getDataHoraChegada();
        this.destinoId = voo.getDestino() != null ? voo.getDestino().getId() : null;
    }

    public Long getId() {
        return id;
    }

    public String getCompanhia() {
        return companhia;
    }

    public String getNumeroVoo() {
        return numeroVoo;
    }

    public String getAeroportoOrigem() {
        return aeroportoOrigem;
    }

    public String getAeroportoDestino() {
        return aeroportoDestino;
    }

    public LocalDateTime getDataHoraPartida() {
        return dataHoraPartida;
    }

    public LocalDateTime getDataHoraChegada() {
        return dataHoraChegada;
    }

    public Long getDestinoId() {
        return destinoId;
    }
}