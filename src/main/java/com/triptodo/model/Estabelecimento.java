package com.triptodo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "estabelecimentos")
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    private String observacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEstabelecimento tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destino_id", nullable = false)
    private Destino destino;

    public Estabelecimento() {
    }

    public Estabelecimento(Long id, String nome, String endereco, String observacao,
                           TipoEstabelecimento tipo, Destino destino) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.observacao = observacao;
        this.tipo = tipo;
        this.destino = destino;
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

    public String getObservacao() {
        return observacao;
    }

    public TipoEstabelecimento getTipo() {
        return tipo;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setTipo(TipoEstabelecimento tipo) {
        this.tipo = tipo;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }
}