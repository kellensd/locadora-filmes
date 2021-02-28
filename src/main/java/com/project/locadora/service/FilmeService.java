package com.project.locadora.service;

import com.project.locadora.dto.FilmeDTO;
import com.project.locadora.exception.FilmeIndisponivelException;

import java.util.List;
import java.util.Optional;

public interface FilmeService {

    Optional<FilmeDTO> findByTitulo(String titulo);

    List<FilmeDTO> findFilmesDisponiveis();

    void locarFilme(String titulo) throws FilmeIndisponivelException;
}
