package com.triptodo.dto;

import com.triptodo.model.TipoEstabelecimento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EstabelecimentoRequestDTO {

    @NotBlank(message = "O nome do estabelecimento é obrigatório.")
    private String nome;

    @NotBlank(message = "O endereço é obrigatório.")
    private String endereco;

    private String observacao;

    @NotNull(message = "O tipo do estabelecimento é obrigatório.")
    private TipoEstabelecimento tipo;

    @NotNull(message = "O ID do destino é obrigatório.")
    private Long destinoId;

    public EstabelecimentoRequestDTO() {
    }

    public EstabelecimentoRequestDTO(String nome, String endereco, String observacao,
                                     TipoEstabelecimento tipo, Long destinoId) {
        this.nome = nome;
        this.endereco = endereco;
        this.observacao = observacao;
        this.tipo = tipo;
        this.destinoId = destinoId;
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

    public void setDestinoId(Long destinoId) {
        this.destinoId = destinoId;
    }
}