package com.triptodo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AtividadeRequestDTO {

    @NotBlank(message = "O título da atividade é obrigatório.")
    private String titulo;

    private String descricao;

    @NotNull(message = "A data da atividade é obrigatória.")
    private LocalDate data;

    private LocalTime horario;

    private String local;

    @NotNull(message = "O ID do destino é obrigatório.")
    private Long destinoId;

    public AtividadeRequestDTO() {
    }

    public AtividadeRequestDTO(String titulo, String descricao, LocalDate data,
                               LocalTime horario, String local, Long destinoId) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.horario = horario;
        this.local = local;
        this.destinoId = destinoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Long getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(Long destinoId) {
        this.destinoId = destinoId;
    }
}