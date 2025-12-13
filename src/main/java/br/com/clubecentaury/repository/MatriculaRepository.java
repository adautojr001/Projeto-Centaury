package br.com.clubecentaury.repository;

import br.com.clubecentaury.model.Matricula;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import br.com.clubecentaury.model.User;

public interface MatriculaRepository extends JpaRepository<Matricula , Long> {
    List<Matricula> findByUserId(Long userId);
    List<Matricula> findByCursoId(Long cursoId);
    @Query("SELECT e.user FROM Matricula e WHERE e.curso.id = :cursoId")
List<User> findUserByCurso(Long cursoId);
    void deleteAllByCursoId(Long cursoId);
}
