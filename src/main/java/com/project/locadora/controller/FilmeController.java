package com.project.locadora.controller;

import com.project.locadora.dto.FilmeDTO;
import com.project.locadora.service.impl.FilmeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Validated
@RestController
@RequestMapping("/filmes")
@Api(tags = "Filmes")
public class FilmeController {

    private FilmeServiceImpl filmeService;

    public FilmeController(FilmeServiceImpl filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping(value = "/{titulo}")
    @ApiOperation(value = "Pesquisa de filme pelo título.")
    public ResponseEntity<Optional<FilmeDTO>> findByTitulo(@PathVariable String titulo) {
        Optional<FilmeDTO> filmeDTO = filmeService.findByTitulo(titulo);

        if (filmeDTO.isPresent()) {
            return ResponseEntity.ok(filmeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //todo
    //listagem de filmes disponíveis
    // locação de um filme
    // devolução de um filme
}
