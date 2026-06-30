package com.triptodo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ViagemRequestDTO {

    @NotBlank(message = "O nome da viagem é obrigatório.")
    private String nome;

    private String descricao;

    @NotNull(message = "A data de início é obrigatória.")
    @FutureOrPresent(message = "A data de início deve ser hoje ou uma data futura.")
    private LocalDate dataInicio;

    @NotNull(message = "A data de fim é obrigatória.")
    @FutureOrPresent(message = "A data de fim deve ser hoje ou uma data futura.")
    private LocalDate dataFim;

    @NotNull(message = "O usuário da viagem é obrigatório.")
    private Long usuarioId;

    public ViagemRequestDTO() {
    }

    public ViagemRequestDTO(String nome, String descricao, LocalDate dataInicio, LocalDate dataFim, Long usuarioId) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.usuarioId = usuarioId;
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