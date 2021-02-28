package com.project.locadora.representation;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class UsuarioIn {

    @ApiModelProperty(notes = "E-mail do Usuário")
    @NotNull
    private String email;

    @ApiModelProperty(notes = "Nome Completo do Usuário")
    @NotNull
    private String nomeCompleto;

    @ApiModelProperty(notes = "Senha do Usuário")
    @NotNull
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
