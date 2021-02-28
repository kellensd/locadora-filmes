package com.project.locadora.model;

import javax.persistence.*;

@Entity
@Table(name = "LOCACAO")
public class Locacao {

    @Id
    @Column(name = "ID_LOCACAO_FILME")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOCACAO_ID_SEQ")
    @SequenceGenerator(name="LOCACAO_ID_SEQ", sequenceName = "LOCACAO_ID_SEQ", allocationSize = 1, initialValue = 2)
    private Long idLocacaoFilme;

    @Column(name = "ID_FILME")
    private String idFilme;

    @Column(name = "ID_USUARIO")
    private String idUsuario;

    public Locacao() {
    }

    public Long getIdLocacaoFilme() {
        return idLocacaoFilme;
    }

    public void setIdLocacaoFilme(Long idLocacaoFilme) {
        this.idLocacaoFilme = idLocacaoFilme;
    }

    //todo gets e sets
}
