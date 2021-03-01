package com.project.locadora.exception;

import org.junit.Test;

import static org.junit.Assert.*;

public class FilmeInexistenteExceptionTest {

    @Test
    public void FilmeInexistenteExceptionTest() {
        FilmeInexistenteException ex = new FilmeInexistenteException("Erro");
        assertEquals("Erro", ex.getMessage());
    }
}