package com.project.locadora.exception;

import org.junit.Test;

import static org.junit.Assert.*;

public class FilmeIndisponivelExceptionTest {

    @Test
    public void FilmeIndisponivelExceptionTest() {
        FilmeIndisponivelException ex = new FilmeIndisponivelException("Erro");
        assertEquals("Erro", ex.getMessage());
    }
}