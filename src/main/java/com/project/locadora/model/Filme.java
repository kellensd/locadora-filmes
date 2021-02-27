package com.project.locadora.model;

import javax.persistence.*;

@Entity
@Table(name = "FILME")
public class Filme {

    @Id
    @Column(name = "ID_FILME")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILME_ID_SEQ")
    @SequenceGenerator(name="FILME_ID_SEQ", sequenceName = "FILME_ID_SEQ", allocationSize = 1, initialValue = 5)
    private Long idFilme;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DIRETOR")
    private String diretor;

    public Filme() {
    }

    public Long getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Long idFilme) {
        this.idFilme = idFilme;
    }
}
