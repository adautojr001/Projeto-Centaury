package br.com.clubecentaury.service;

import br.com.clubecentaury.model.Curso;
import br.com.clubecentaury.repository.CursoRepository;
import br.com.clubecentaury.repository.MatriculaRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    
    private CursoRepository cursoRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    public List<Curso> getCursosComAlunos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> getCursoById(Long id) {
        return cursoRepository.findById(id);
    }

    @Transactional
    public Curso createCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Transactional
    public Curso updateCurso(Long id, Curso curso) {
        Curso existente = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        existente.setNome(curso.getNome());
        existente.setDescricao(curso.getDescricao());

        return cursoRepository.save(existente);
    }

    @Transactional
    public void deleteCurso(Long id) {
        matriculaRepository.deleteAllByCursoId(id);
        cursoRepository.deleteById(id);
    }

    @Bean
public CommandLineRunner testCurso(CursoRepository cursoRepository) {
    return args -> {
        List<Curso> cursos = cursoRepository.findAll();
        if(cursos.isEmpty()) {
            System.out.println("Nenhum curso encontrado!");
        } else {
            cursos.forEach(c -> System.out.println(c.getNome()));
        }
    };
}
}





















