package com.project.locadora.repository;

import com.project.locadora.model.Filme;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FilmeRepositoryTest {

    @Autowired
    private FilmeRepository filmeRepository;

    @Test
    public void findByTituloIgnoreCaseTest() {
        Optional<Filme> filme = filmeRepository.findByTituloIgnoreCase("venom");
        assertEquals("Ruben Fleischer", filme.get().getDiretor());
    }

    @Test
    public void findByQuantidadeGreaterThanZeroTest() {
        List<Filme> filme = filmeRepository.findByQuantidadeGreaterThan(0)
                .stream()
                .collect(Collectors.toList());
        assertEquals(3, filme.size());
    }

    @Test
    public void findByQuantidadeGreaterThanOneTest() {
        List<Filme> filme = filmeRepository.findByQuantidadeGreaterThan(1)
                .stream()
                .collect(Collectors.toList());
        assertEquals(1, filme.size());
    }
}