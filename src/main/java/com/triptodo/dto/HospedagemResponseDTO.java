package com.triptodo.dto;

import java.time.LocalDate;

import com.triptodo.model.Hospedagem;

public class HospedagemResponseDTO {

    private Long id;
    private String nome;
    private String endereco;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String observacao;
    private Long destinoId;

    public HospedagemResponseDTO() {
    }

    public HospedagemResponseDTO(Long id, String nome, String endereco, LocalDate checkIn,
                                 LocalDate checkOut, String observacao, Long destinoId) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.observacao = observacao;
        this.destinoId = destinoId;
    }

    public HospedagemResponseDTO(Hospedagem hospedagem) {
        this.id = hospedagem.getId();
        this.nome = hospedagem.getNome();
        this.endereco = hospedagem.getEndereco();
        this.checkIn = hospedagem.getCheckIn();
        this.checkOut = hospedagem.getCheckOut();
        this.observacao = hospedagem.getObservacao();
        this.destinoId = hospedagem.getDestino() != null ? hospedagem.getDestino().getId() : null;
    }

    public Long getId() {
        return id;
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
}