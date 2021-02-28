package com.project.locadora.service.impl;

import com.project.locadora.dto.FilmeDTO;
import com.project.locadora.exception.FilmeIndisponivelException;
import com.project.locadora.exception.FilmeInexistenteException;
import com.project.locadora.model.Filme;
import com.project.locadora.repository.FilmeRepository;
import com.project.locadora.service.FilmeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return filmeRepository.findByTituloIgnoreCase(titulo)
                .map(filme -> modelMapper.map(filme, FilmeDTO.class));
    }

    @Override
    public List<FilmeDTO> findFilmesDisponiveis() {
        return filmeRepository.findByQuantidadeGreaterThan(0)
                .stream()
                .map(filme -> modelMapper.map(filme, FilmeDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public void locarFilme(String titulo) throws FilmeIndisponivelException {
        Filme filme = filmeRepository.findByTituloIgnoreCase(titulo)
                .filter(filme1 -> filme1.getQuantidade() > 0)
                .orElseThrow(() -> new FilmeIndisponivelException("Filme indisponível para alugar!"));

        filme.locarFilme();
        filmeRepository.save(filme);
    }

    @Override
    public void devolverFilme(String titulo) throws FilmeInexistenteException {
        Filme filme = filmeRepository.findByTituloIgnoreCase(titulo)
                .orElseThrow(() -> new FilmeInexistenteException("Filme não existe no sistema!"));

        filme.devolverFilme();
        filmeRepository.save(filme);
    }

    @Override
    public Filme create(FilmeDTO filmeDTO) {
        Filme filme = new Filme(filmeDTO);
        return filmeRepository.save(filme);
    }

    @Override
    public Optional<Filme> findById(Long id) {
        return filmeRepository.findById(id);
    }

    @Override
    public void delete(Filme filme) {
        filmeRepository.delete(filme);
    }
}
