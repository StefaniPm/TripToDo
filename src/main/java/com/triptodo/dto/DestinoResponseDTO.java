package com.triptodo.dto;

import java.time.LocalDate;

import com.triptodo.model.Destino;

public class DestinoResponseDTO {

    private Long id;
    private String nome;
    private String cidade;
    private String pais;
    private LocalDate dataChegada;
    private LocalDate dataSaida;
    private Long viagemId;

    public DestinoResponseDTO() {
    }

    public DestinoResponseDTO(Long id, String nome, String cidade, String pais,
                              LocalDate dataChegada, LocalDate dataSaida, Long viagemId) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.pais = pais;
        this.dataChegada = dataChegada;
        this.dataSaida = dataSaida;
        this.viagemId = viagemId;
    }

    public DestinoResponseDTO(Destino destino) {
        this.id = destino.getId();
        this.nome = destino.getNome();
        this.cidade = destino.getCidade();
        this.pais = destino.getPais();
        this.dataChegada = destino.getDataChegada();
        this.dataSaida = destino.getDataSaida();
        this.viagemId = destino.getViagem() != null ? destino.getViagem().getId() : null;
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