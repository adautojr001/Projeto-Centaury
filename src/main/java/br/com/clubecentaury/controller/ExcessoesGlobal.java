package br.com.clubecentaury.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExcessoesGlobal {

    @ExceptionHandler(Excessoes.class)
    public Map<String, String> handleExcessoes(Excessoes ex) {
        return Map.of(
            "error", ex.getMessage()
        );
    }
}

