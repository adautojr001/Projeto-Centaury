package br.com.clubecentaury.controller;

import br.com.clubecentaury.model.Curso;
import br.com.clubecentaury.service.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/cursos")
@CrossOrigin("*")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // LISTAR TODOS
    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        return ResponseEntity.ok(cursoService.getCursosComAlunos());
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Long id) {
        return cursoService.getCursoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CRIAR CURSO
    @PostMapping
    public ResponseEntity<Curso> criarCurso(@RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.createCurso(curso));
    }

    // ATUALIZAR CURSO
    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        try {
            Curso atualizado = cursoService.updateCurso(id, curso);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETAR CURSO
    @DeleteMapping("/{id}")
public ResponseEntity<?> deletarCurso(@PathVariable Long id) {
    try {
        cursoService.deleteCurso(id);
        return ResponseEntity.ok().body("Curso deletado com sucesso!");
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}

}













