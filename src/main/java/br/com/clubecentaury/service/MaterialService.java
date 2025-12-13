package br.com.clubecentaury.service;

import br.com.clubecentaury.model.Material;
import br.com.clubecentaury.repository.MaterialRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MaterialService {
    private final MaterialRepository repository;

    public MaterialService(MaterialRepository repository) {
        this.repository = repository;
    }

    public List<Material> getMateriaisByCurso(Long cursoId) {
        return repository.findByCursoId(cursoId);
    }

    public Material createMaterial(Material material) {
        return repository.save(material);
    }
}

