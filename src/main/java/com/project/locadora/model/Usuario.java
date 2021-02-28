package com.project.locadora.model;

import com.project.locadora.representation.UsuarioIn;

import javax.persistence.*;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @Column(name = "ID_USUARIO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_ID_SEQ")
    @SequenceGenerator(name="USUARIO_ID_SEQ", sequenceName = "USUARIO_ID_SEQ", allocationSize = 1, initialValue = 2)
    private Long idUsuario;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NOME_COMPLETO")
    private String nomeCompleto;

    @Column(name = "SENHA")
    private String senha;

    public Usuario() {
    }

    public Usuario(UsuarioIn usuarioIn) {
        this.email = usuarioIn.getEmail();
        this.nomeCompleto = usuarioIn.getNomeCompleto();
        this.senha = usuarioIn.getSenha();
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

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
