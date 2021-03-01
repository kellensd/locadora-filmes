package com.project.locadora.service.impl;

import com.project.locadora.dto.FilmeDTO;
import com.project.locadora.model.Filme;
import com.project.locadora.repository.FilmeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FilmeServiceImplTest {

    @Mock
    private FilmeRepository filmeRepository;

    @InjectMocks
    private FilmeServiceImpl filmeService;

    @Mock
    FilmeDTO filmeDTO;
    @Mock
    Filme filme;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        filmeDTO = new FilmeDTO();
        filmeDTO.setDiretor("Eu");
        filmeDTO.setQuantidade(10);
        filmeDTO.setTitulo("cascata");

        filme = new Filme(filmeDTO);
    }

    @Test
    public void findByTituloTest() {
        when(filmeService.findByTitulo("cascata")).thenReturn(Optional.of(filme));

        Optional<Filme> filme = filmeService.findByTitulo("cascata");
        assertEquals(10, filme.get().getQuantidade());
    }

    @Test
    public void findFilmesDisponiveis() {
        when(filmeService.findFilmesDisponiveis()).thenReturn(List.of(filme));

        List<Filme> filmes = filmeService.findFilmesDisponiveis();
        assertEquals("Eu", filmes.stream().findFirst().get().getDiretor());
    }

    @Test
    public void findById() {
        when(filmeService.findById(1L)).thenReturn(Optional.of(filme));

        Optional<Filme> filme = filmeService.findById(1L);
        assertEquals("Eu", filme.get().getDiretor());
    }
}