package br.com.clubecentaury.service;

import br.com.clubecentaury.model.Matricula;
import br.com.clubecentaury.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    public List<Matricula> getMatriculasPorCurso(Long cursoId) {
    return matriculaRepository.findByCursoId(cursoId);
}
    public List<Matricula> getAll() {
        return matriculaRepository.findAll();
    }

    public Optional<Matricula> getById(Long id) {
        return matriculaRepository.findById(id);
    }
      public void confirmarMatricula(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
        matricula.setConfirmada(true); // adicionar campo boolean confirmada em Matricula
        matriculaRepository.save(matricula);
    }

    public Matricula createMatricula(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    public void deleteMatricula(Long id) {
        matriculaRepository.deleteById(id);
    }
}









