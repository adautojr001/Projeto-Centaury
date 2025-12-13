package br.com.clubecentaury.dto;

import java.util.List;

public class CursoComAlunosDTO {

    private Long cursoId;
    private String nome;
    private String descricao;
    private List<MatriculaDTO> alunos;

    public CursoComAlunosDTO(Long cursoId, String nome, String descricao, List<MatriculaDTO> alunos) {
        this.cursoId = cursoId;
        this.nome = nome;
        this.descricao = descricao;
        this.alunos = alunos;
    }

    // Getters e Setters
    public Long getCursoId() { return cursoId; }
    public void setCursoId(Long cursoId) { this.cursoId = cursoId; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public List<MatriculaDTO> getAlunos() { return alunos; }
    public void setAlunos(List<MatriculaDTO> alunos) { this.alunos = alunos; }
}




