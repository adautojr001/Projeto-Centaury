package br.com.clubecentaury.repository;

import br.com.clubecentaury.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByCursoId(Long cursoId);
}

