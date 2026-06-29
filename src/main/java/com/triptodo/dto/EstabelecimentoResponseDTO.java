package com.triptodo.dto;

import com.triptodo.model.Estabelecimento;
import com.triptodo.model.TipoEstabelecimento;

public class EstabelecimentoResponseDTO {

    private Long id;
    private String nome;
    private String endereco;
    private String observacao;
    private TipoEstabelecimento tipo;
    private Long destinoId;

    public EstabelecimentoResponseDTO() {
    }

    public EstabelecimentoResponseDTO(Long id, String nome, String endereco, String observacao,
                                      TipoEstabelecimento tipo, Long destinoId) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.observacao = observacao;
        this.tipo = tipo;
        this.destinoId = destinoId;
    }

    public EstabelecimentoResponseDTO(Estabelecimento estabelecimento) {
        this.id = estabelecimento.getId();
        this.nome = estabelecimento.getNome();
        this.endereco = estabelecimento.getEndereco();
        this.observacao = estabelecimento.getObservacao();
        this.tipo = estabelecimento.getTipo();
        this.destinoId = estabelecimento.getDestino() != null ? estabelecimento.getDestino().getId() : null;
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

    public Long getDestinoId() {
        return destinoId;
    }
}