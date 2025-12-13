package br.com.clubecentaury.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "🚀 API Moodle Clube Centaury está rodando sem restrições!";
    }
}
