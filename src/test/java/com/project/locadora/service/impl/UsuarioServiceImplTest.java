package com.project.locadora.service.impl;

import com.project.locadora.model.Usuario;
import com.project.locadora.repository.UsuarioRepository;
import com.project.locadora.representation.UsuarioIn;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    Usuario usuario;
    UsuarioIn usuarioIn;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        usuarioIn = new UsuarioIn();
        usuarioIn.setNome("Eu");
        usuarioIn.setSenha("teste111");
        usuarioIn.setEmail("eu@gmail.com");

        usuario = new Usuario(usuarioIn);
    }

    @Test
    public void findByIdTest() {
        when(usuarioService.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> usuario = usuarioService.findById(1L);
        assertEquals("eu@gmail.com", usuario.get().getEmail());
        assertEquals(usuarioIn.getSenha(), usuario.get().getSenha());
    }
}