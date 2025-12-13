package br.com.clubecentaury.dto;

public class MatriculaDTO {

    private Long matriculaId;
    private Long userId;
    private String userNome;

    public MatriculaDTO(Long matriculaId, Long userId, String userNome) {
        this.matriculaId = matriculaId;
        this.userId = userId;
        this.userNome = userNome;
    }

    // Getters e Setters
    public Long getMatriculaId() { return matriculaId; }
    public void setMatriculaId(Long matriculaId) { this.matriculaId = matriculaId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUserNome() { return userNome; }
    public void setUserNome(String userNome) { this.userNome = userNome; }
}



