package br.gov.mt.sedec.controlesecretaria.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound()
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest()
                .body(erros.stream()
                        .map(DadosErroValidacao::new)
                        .toList());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity tratarErroDeValidacao(ConstraintViolationException ex) {
        var erros = ex.getConstraintViolations();

        return ResponseEntity.badRequest()
                .body(erros.stream()
                        .map(erro -> new DadosErroValidacao(erro.getPropertyPath()
                                .toString(), erro.getMessage()))
                        .toList());
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}