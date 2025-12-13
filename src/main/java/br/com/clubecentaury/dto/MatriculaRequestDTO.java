package br.com.clubecentaury.dto;

public class MatriculaRequestDTO {
    private Long userId;
    private Long cursoId;

    public MatriculaRequestDTO() {} // necessário para desserialização

    public MatriculaRequestDTO(Long userId, Long cursoId) {
        this.userId = userId;
        this.cursoId = cursoId;
    }

    // Getters e Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getCursoId() { return cursoId; }
    public void setCursoId(Long cursoId) { this.cursoId = cursoId; }
}

