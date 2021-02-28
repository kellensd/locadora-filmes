package com.project.locadora.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class FilmeDTO {

    @ApiModelProperty(notes = "Titulo do Filme")
    @NotNull
    private String titulo;

    @ApiModelProperty(notes = "Diretor do Filme")
    @NotNull
    private String diretor;

    @ApiModelProperty(notes = "Quantidade do Filme")
    @NotNull
    private int quantidade;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
