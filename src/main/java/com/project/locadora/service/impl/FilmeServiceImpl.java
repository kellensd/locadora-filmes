package com.project.locadora.service.impl;

import com.project.locadora.dto.FilmeDTO;
import com.project.locadora.repository.FilmeRepository;
import com.project.locadora.service.FilmeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FilmeServiceImpl implements FilmeService {

    private FilmeRepository filmeRepository;

    private ModelMapper modelMapper;

    public FilmeServiceImpl(FilmeRepository filmeRepository, ModelMapper modelMapper) {
        this.filmeRepository = filmeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<FilmeDTO> findByTitulo(String titulo) {
        return filmeRepository.findByTitulo(titulo)
                .map(filme -> modelMapper.map(filme, FilmeDTO.class));
    }
}
