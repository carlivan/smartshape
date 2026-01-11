package com.carlivan.smartshape.api.exception;

import com.carlivan.smartshape.api.dto.ErroResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {

    // Tratamento caso um ID não exista(404)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroResponse> handleNotfound(EntityNotFoundException ex,
                                                       HttpServletRequest request) {
        var erro = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                ex.getMessage(),
                request.getRequestURI(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }


    // Tratamento para erros de validação (@Valid - 400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handle400(MethodArgumentNotValidException ex,
                                                              HttpServletRequest request) {

        var campos = ex.getFieldErrors().stream()
                .map(f-> new ErroResponse.CampoErro(
                        f.getField(),
                        f.getDefaultMessage()))
                .toList();

        var erro = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                "Um ou mais campos estão inválidos",
                request.getRequestURI(),
                campos
        );
        return ResponseEntity.badRequest().body(erro);
    }
}
