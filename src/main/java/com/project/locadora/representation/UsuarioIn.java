package com.project.locadora.representation;

import com.project.locadora.business.Criptografia;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class UsuarioIn {

    @ApiModelProperty(notes = "E-mail do Usuário")
    @NotNull
    private String email;

    @ApiModelProperty(notes = "Nome do Usuário")
    @NotNull
    private String nome;

    @ApiModelProperty(notes = "Senha do Usuário")
    @NotNull
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = Criptografia.getHashMd5(senha);
    }
}
