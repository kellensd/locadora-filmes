package com.project.locadora.controller;

import com.project.locadora.model.Usuario;
import com.project.locadora.representation.UsuarioIn;
import com.project.locadora.service.impl.UsuarioServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/usuarios")
@Api(tags = "Usuarios")
public class UsuarioController {

    private UsuarioServiceImpl usuarioService;

    public UsuarioController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @ApiOperation(value = "Cadastrar um usuario.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Usuário cadastrado com sucesso."),
                    @ApiResponse(code = 500, message = "Ocorreu um erro inesperado no servidor ao cadastrar o usuário.")
            }
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity create(@RequestBody UsuarioIn usuarioIn) throws URISyntaxException {
        Usuario usuario = usuarioService.create(usuarioIn);
        return ResponseEntity.created(new URI(String.format("/usuarios/%d", usuario.getIdUsuario())))
                .build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Excluir um usuário.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 204, message = "Usuário excluído com sucesso."),
                    @ApiResponse(code = 404, message = "Usuário não encontrado."),
                    @ApiResponse(code = 500, message = "Ocorreu um erro inesperado no servidor ao excluir o usuário.")
            }
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deleteById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);

        if (usuario.isPresent()) {
            usuarioService.delete(usuario.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
