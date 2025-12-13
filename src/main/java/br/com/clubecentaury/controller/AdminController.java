package br.com.clubecentaury.controller;

import br.com.clubecentaury.model.Curso;
import br.com.clubecentaury.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CursoService cursoService;

    @PostMapping("/cursos")
    public Curso criarCurso(@RequestBody Curso curso) {
        return cursoService.createCurso(curso);
    }
}



