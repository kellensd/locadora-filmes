package com.project.locadora.service.impl;

import com.project.locadora.dto.FilmeDTO;
import com.project.locadora.exception.FilmeIndisponivelException;
import com.project.locadora.exception.FilmeInexistenteException;
import com.project.locadora.model.Filme;
import com.project.locadora.repository.FilmeRepository;
import com.project.locadora.service.FilmeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeServiceImpl implements FilmeService {

    private FilmeRepository filmeRepository;

    public FilmeServiceImpl(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Override
    public Optional<Filme> findByTitulo(String titulo) {
        return filmeRepository.findByTituloIgnoreCase(titulo);
    }

    @Override
    public List<Filme> findFilmesDisponiveis() {
        return filmeRepository.findByQuantidadeGreaterThan(0);

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
