package com.triptodo.dto;

import java.time.LocalDate;

import com.triptodo.model.Viagem;

public class ViagemResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Long usuarioId;

    public ViagemResponseDTO() {
    }

    public ViagemResponseDTO(Long id, String nome, String descricao, LocalDate dataInicio, LocalDate dataFim, Long usuarioId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.usuarioId = usuarioId;
    }

    public ViagemResponseDTO(Viagem viagem) {
        this.id = viagem.getId();
        this.nome = viagem.getNome();
        this.descricao = viagem.getDescricao();
        this.dataInicio = viagem.getDataInicio();
        this.dataFim = viagem.getDataFim();
        this.usuarioId = viagem.getUsuario() != null ? viagem.getUsuario().getId() : null;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}