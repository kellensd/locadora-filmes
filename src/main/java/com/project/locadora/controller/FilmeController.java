package com.project.locadora.controller;

import com.project.locadora.dto.FilmeDTO;
import com.project.locadora.dto.ResponseErrorDTO;
import com.project.locadora.exception.FilmeIndisponivelException;
import com.project.locadora.service.impl.FilmeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/filmes")
@Api(tags = "Filmes")
public class FilmeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilmeController.class);

    private FilmeServiceImpl filmeService;

    public FilmeController(FilmeServiceImpl filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping(value = "/{titulo}")
    @ApiOperation(value = "Pesquisa de filme pelo título.")
    public ResponseEntity<Optional<FilmeDTO>> findByTitulo(@PathVariable String titulo) {
        Optional<FilmeDTO> filme = filmeService.findByTitulo(titulo);

        if (filme.isPresent()) {
            return ResponseEntity.ok(filme);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/disponiveis")
    @ApiOperation(value = "Consulta de filmes disponíveis.")
    public ResponseEntity<List<FilmeDTO>> findFilmesDisponiveis() {
        List<FilmeDTO> filmes = filmeService.findFilmesDisponiveis();

        if (!filmes.isEmpty()) {
            return ResponseEntity.ok(filmes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(value = "/locacao/{titulo}")
    @ApiOperation(value = "Locação de filme.")
    public ResponseEntity locarFilme(@PathVariable String titulo) {
        Optional<FilmeDTO> filme = filmeService.findByTitulo(titulo);

        if (filme.isPresent()) {
            try {
                filmeService.locarFilme(titulo);
            } catch (FilmeIndisponivelException ex) {
                LOGGER.error("Erro ao locar filme!", ex);
                return ResponseEntity.badRequest().body(new ResponseErrorDTO(ex.getMessage()));
            }
            return ResponseEntity.ok("Filme alugado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //todo
    // devolução de um filme
}
