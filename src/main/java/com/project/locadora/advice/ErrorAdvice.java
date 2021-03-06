package com.project.locadora.advice;

import com.project.locadora.dto.ResponseErrorDTO;
import com.project.locadora.exception.FilmeIndisponivelException;
import com.project.locadora.exception.FilmeInexistenteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorAdvice.class);

    @ExceptionHandler
    public ResponseEntity<ResponseErrorDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        LOGGER.error("Erro ao cadastrar!", ex);
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO("Cadastro já existente na base!");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(responseErrorDTO);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseErrorDTO> handleFilmeInexistenteException(FilmeInexistenteException ex) {
        LOGGER.error("Erro ao devolver filme!", ex);
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(responseErrorDTO);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseErrorDTO> handleFilmeIndisponivelException(FilmeIndisponivelException ex) {
        LOGGER.error("Erro ao alugar filme!", ex);
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(responseErrorDTO);
    }
}
