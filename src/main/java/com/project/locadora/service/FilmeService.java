package com.project.locadora.service;

import com.project.locadora.dto.FilmeDTO;
import com.project.locadora.exception.FilmeIndisponivelException;
import com.project.locadora.exception.FilmeInexistenteException;
import com.project.locadora.model.Filme;

import java.util.List;
import java.util.Optional;

public interface FilmeService {

    Optional<Filme> findByTitulo(String titulo);

    List<Filme> findFilmesDisponiveis();

    void locarFilme(String titulo) throws FilmeIndisponivelException;

    void devolverFilme(String titulo) throws FilmeInexistenteException;

    Filme create(FilmeDTO filmeDTO);

    Optional<Filme> findById(Long id);

    void delete(Filme filme);
}
