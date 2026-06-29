package com.triptodo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HospedagemRequestDTO {

    @NotBlank(message = "O nome da hospedagem é obrigatório.")
    private String nome;

    @NotBlank(message = "O endereço é obrigatório.")
    private String endereco;

    @NotNull(message = "O check-in é obrigatório.")
    private LocalDate checkIn;

    @NotNull(message = "O check-out é obrigatório.")
    private LocalDate checkOut;

    private String observacao;

    @NotNull(message = "O ID do destino é obrigatório.")
    private Long destinoId;

    public HospedagemRequestDTO() {
    }

    public HospedagemRequestDTO(String nome, String endereco, LocalDate checkIn,
                                LocalDate checkOut, String observacao, Long destinoId) {
        this.nome = nome;
        this.endereco = endereco;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.observacao = observacao;
        this.destinoId = destinoId;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public String getObservacao() {
        return observacao;
    }

    public Long getDestinoId() {
        return destinoId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setDestinoId(Long destinoId) {
        this.destinoId = destinoId;
    }
}