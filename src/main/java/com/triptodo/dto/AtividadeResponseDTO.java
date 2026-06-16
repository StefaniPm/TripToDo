package com.triptodo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.triptodo.model.Atividade;

public class AtividadeResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate data;
    private LocalTime horario;
    private String local;
    private Long destinoId;

    public AtividadeResponseDTO() {
    }

    public AtividadeResponseDTO(Long id, String titulo, String descricao, LocalDate data,
                                LocalTime horario, String local, Long destinoId) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.horario = horario;
        this.local = local;
        this.destinoId = destinoId;
    }

    public AtividadeResponseDTO(Atividade atividade) {
        this.id = atividade.getId();
        this.titulo = atividade.getTitulo();
        this.descricao = atividade.getDescricao();
        this.data = atividade.getData();
        this.horario = atividade.getHorario();
        this.local = atividade.getLocal();
        this.destinoId = atividade.getDestino() != null ? atividade.getDestino().getId() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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