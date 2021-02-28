package com.project.locadora.service;

import com.project.locadora.dto.FilmeDTO;

import java.util.Optional;

public interface FilmeService {

    Optional<FilmeDTO> findByTitulo(String titulo);

}
