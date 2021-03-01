package com.project.locadora.repository;

import com.project.locadora.model.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void findByNomeIgnoreCaseTest() {
        Optional<Usuario> usuario = usuarioRepository.findByNomeIgnoreCase("KELLEN");
        assertEquals("kellen@gmail.com", usuario.get().getEmail());
    }

}