package br.com.clubecentaury.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clubecentaury.model.Material;
import br.com.clubecentaury.service.MaterialService;

@RestController
@RequestMapping("/materiais")
@CrossOrigin(origins = "*")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping("/curso/{cursoId}")
    public List<Material> getMateriaisPorCurso(@PathVariable Long cursoId) {
        return materialService.getMateriaisByCurso(cursoId);
    }

    @PostMapping("/add")
    public Material addMaterial(@RequestBody Material material) {
        return materialService.createMaterial(material);
    }
}

