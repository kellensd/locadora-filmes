package com.project.locadora.model;

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

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
