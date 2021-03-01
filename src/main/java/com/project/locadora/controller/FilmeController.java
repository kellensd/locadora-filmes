package com.project.locadora.controller;

import com.project.locadora.dto.FilmeDTO;
import com.project.locadora.dto.ResponseErrorDTO;
import com.project.locadora.exception.FilmeIndisponivelException;
import com.project.locadora.exception.FilmeInexistenteException;
import com.project.locadora.model.Filme;
import com.project.locadora.service.impl.FilmeServiceImpl;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/filmes")
@Api(tags = "Filmes")
public class FilmeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilmeController.class);

    private FilmeServiceImpl filmeService;

    private ModelMapper modelMapper;

    public FilmeController(FilmeServiceImpl filmeService, ModelMapper modelMapper) {
        this.filmeService = filmeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/{titulo}")
    @ApiOperation(value = "Pesquisa de filme pelo título.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Filme retornado com sucesso.", response = FilmeDTO.class),
                    @ApiResponse(code = 404, message = "Filme não encontrado."),
                    @ApiResponse(code = 500, message = "Ocorreu um erro inesperado no servidor ao pesquisar o filme.")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FilmeDTO> findByTitulo(@PathVariable String titulo) {
        Optional<Filme> filme = filmeService.findByTitulo(titulo);

        if (filme.isPresent()) {
            return ResponseEntity.ok(filme.map(film -> modelMapper.map(film, FilmeDTO.class))
                    .get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/disponiveis")
    @ApiOperation(value = "Consulta de filmes disponíveis.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Lista de filmes retornada com sucesso.",
                            response = FilmeDTO.class, responseContainer = "List"),
                    @ApiResponse(code = 404, message = "Lista de filmes não encontrada."),
                    @ApiResponse(code = 500, message = "Ocorreu um erro inesperado no servidor ao pesquisar os filmes disponíveis.")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<FilmeDTO>> findFilmesDisponiveis() {
        List<Filme> filmes = filmeService.findFilmesDisponiveis();

        if (!filmes.isEmpty()) {
            return ResponseEntity.ok(filmes
                    .stream()
                    .map(filme -> modelMapper.map(filme, FilmeDTO.class))
                    .collect(Collectors.toList()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(value = "/locacao/{titulo}")
    @ApiOperation(value = "Locação de filme.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Filme alugado com sucesso."),
                    @ApiResponse(code = 400, message = "Filme indisponível para alugar!", response = ResponseErrorDTO.class),
                    @ApiResponse(code = 404, message = "Filme não encontrado."),
                    @ApiResponse(code = 500, message = "Ocorreu um erro inesperado no servidor ao alugar o filme.")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity locarFilme(@PathVariable String titulo) {
        Optional<Filme> filme = filmeService.findByTitulo(titulo);

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

    @PatchMapping(value = "/devolucao/{titulo}")
    @ApiOperation(value = "Devolução de filme.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Filme devolvido com sucesso."),
                    @ApiResponse(code = 400, message = "Filme não existe no sistema!", response = ResponseErrorDTO.class),
                    @ApiResponse(code = 404, message = "Filme não encontrado."),
                    @ApiResponse(code = 500, message = "Ocorreu um erro inesperado no servidor ao devolver o filme.")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity devolverFilme(@PathVariable String titulo) {
        Optional<Filme> filme = filmeService.findByTitulo(titulo);

        if (filme.isPresent()) {
            try {
                filmeService.devolverFilme(titulo);
            } catch (FilmeInexistenteException ex) {
                LOGGER.error("Erro ao devolver filme!", ex);
                return ResponseEntity.badRequest().body(new ResponseErrorDTO(ex.getMessage()));
            }
            return ResponseEntity.ok("Filme devolvido com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ApiOperation(value = "Cadastrar um filme.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Filme cadastrado com sucesso."),
                    @ApiResponse(code = 400, message = "Filme já existente na base. Cadastre outro!"),
                    @ApiResponse(code = 500, message = "Ocorreu um erro inesperado no servidor ao cadastrar o filme.")
            }
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity create(@Valid @RequestBody FilmeDTO filmeDTO) throws URISyntaxException {
        Filme filme = filmeService.create(filmeDTO);
        return ResponseEntity.created(new URI(String.format("/filmes/%d", filme.getIdFilme())))
                .build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Excluir um filme.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 204, message = "Filme excluído com sucesso."),
                    @ApiResponse(code = 404, message = "Filme não encontrado."),
                    @ApiResponse(code = 500, message = "Ocorreu um erro inesperado no servidor ao excluir o filme.")
            }
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deleteById(@PathVariable Long id) {
        Optional<Filme> filme = filmeService.findById(id);

        if (filme.isPresent()) {
            filmeService.delete(filme.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler
    public ResponseEntity<ResponseErrorDTO> handleConstraintViolation(DataIntegrityViolationException ex) {
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO("Filme já existente na base. Cadastre outro!");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(responseErrorDTO);
    }
}
