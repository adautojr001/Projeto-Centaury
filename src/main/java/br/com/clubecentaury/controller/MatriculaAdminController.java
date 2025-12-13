package br.com.clubecentaury.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.clubecentaury.model.User;
import br.com.clubecentaury.service.MatriculaService;
import br.com.clubecentaury.service.UserService;

@RestController
@RequestMapping("/admin/matriculas")
@CrossOrigin(origins = "*")
public class MatriculaAdminController {

    private final MatriculaService matriculaService;
    private final UserService userService; // para buscar usuário logado

    public MatriculaAdminController(MatriculaService matriculaService, UserService userService) {
        this.matriculaService = matriculaService;
        this.userService = userService;
    }

    @PostMapping("/confirmar/{matriculaId}")
    public ResponseEntity<String> confirmarMatricula(@PathVariable Long matriculaId, @RequestParam Long userId) {
        User user = userService.findById(userId); // pegar usuário que está fazendo a ação

        if(!"ADMIN".equals(user.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Ação permitida apenas para admins");
        }

        matriculaService.confirmarMatricula(matriculaId);
        return ResponseEntity.ok("Matrícula confirmada com sucesso ");
    }
}

