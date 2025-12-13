package br.com.clubecentaury.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class Excessoes extends RuntimeException {
    public Excessoes(String message) {
        super(message);
    }



    @ExceptionHandler(Exception.class)
    public Map<String, String> handleGenericException(Exception ex) {
        return Map.of(
            "error", "Erro inesperado: " + ex.getMessage()
        );
    }
}
