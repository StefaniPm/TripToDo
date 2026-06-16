package com.triptodo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DestinoRequestDTO {

    @NotBlank(message = "O nome do destino é obrigatório.")
    private String nome;

    @NotBlank(message = "A cidade é obrigatória.")
    private String cidade;

    @NotBlank(message = "O país é obrigatório.")
    private String pais;

    private LocalDate dataChegada;

    private LocalDate dataSaida;

    @NotNull(message = "O ID da viagem é obrigatório.")
    private Long viagemId;

    public DestinoRequestDTO() {
    }

    public DestinoRequestDTO(String nome, String cidade, String pais,
                             LocalDate dataChegada, LocalDate dataSaida, Long viagemId) {
        this.nome = nome;
        this.cidade = cidade;
        this.pais = pais;
        this.dataChegada = dataChegada;
        this.dataSaida = dataSaida;
        this.viagemId = viagemId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public LocalDate getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(LocalDate dataChegada) {
        this.dataChegada = dataChegada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Long getViagemId() {
        return viagemId;
    }

    public void setViagemId(Long viagemId) {
        this.viagemId = viagemId;
    }
}