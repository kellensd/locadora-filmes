package com.project.locadora.service;

import com.project.locadora.model.Usuario;
import com.project.locadora.representation.UsuarioIn;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario create(UsuarioIn usuarioIn);

    Optional<Usuario> findById(Long id);

    void delete(Usuario usuario);

    List<Usuario> findAll();
}
