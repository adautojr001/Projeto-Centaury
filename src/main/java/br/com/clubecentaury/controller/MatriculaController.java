package br.com.clubecentaury.controller;

import java.util.List;

import br.com.clubecentaury.model.Curso;
import br.com.clubecentaury.model.Matricula;
import br.com.clubecentaury.model.User;
import br.com.clubecentaury.service.CursoService;
import br.com.clubecentaury.service.MatriculaService;
import br.com.clubecentaury.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matriculas")
@CrossOrigin(origins = "*")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private UserService userService;

    @Autowired
    private CursoService cursoService;

    // DTO para receber os dados da requisição
    public static class MatriculaRequest {
        private Long userId;
        private Long cursoId;

        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }

        public Long getCursoId() { return cursoId; }
        public void setCursoId(Long cursoId) { this.cursoId = cursoId; }
    }

    @PostMapping("/matricular")
    public ResponseEntity<?> matricular(@RequestBody MatriculaRequest request) {
        try {
            // Recupera o usuário
            User user = userService.getUserById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            // Recupera o curso
            Curso curso = cursoService.getCursoById(request.getCursoId())
                    .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

            // Cria a matrícula
            Matricula matricula = new Matricula();
            matricula.setUser(user);
            matricula.setCurso(curso);

            Matricula salva = matriculaService.createMatricula(matricula);
            return ResponseEntity.ok(salva);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body("Erro ao matricular: " + e.getMessage());
        }
    }
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Matricula>> getMatriculasPorCurso(@PathVariable Long cursoId) {
    try {
        List<Matricula> matriculas = matriculaService.getMatriculasPorCurso(cursoId);
        return ResponseEntity.ok(matriculas);
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().build();
    }
}
}













