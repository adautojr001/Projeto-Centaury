package br.com.clubecentaury.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    @GetMapping("/teste2")
    public String teste2() {
        return "Controller 2 funcionando!";
    }
}

