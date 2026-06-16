package com.triptodo.dto;

import com.triptodo.model.Viagem;

import java.time.LocalDate;

public class ViagemResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public ViagemResponseDTO() {
    }

    public ViagemResponseDTO(Long id, String nome, String descricao, LocalDate dataInicio, LocalDate dataFim) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public ViagemResponseDTO(Viagem viagem) {
        this.id = viagem.getId();
        this.nome = viagem.getNome();
        this.descricao = viagem.getDescricao();
        this.dataInicio = viagem.getDataInicio();
        this.dataFim = viagem.getDataFim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}