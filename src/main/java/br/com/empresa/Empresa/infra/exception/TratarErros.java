package br.com.empresa.Empresa.infra.exception;

import br.com.empresa.Empresa.domain.ValidarException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratarErros {

    @ExceptionHandler(ValidarException.class)
    public ResponseEntity tratarErroRegraDeNegocio(ValidarException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
