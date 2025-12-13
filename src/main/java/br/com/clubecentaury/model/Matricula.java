package br.com.clubecentaury.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    
    @JoinColumn(name = "curso_id")
   @JsonIgnore
private Curso curso;
        private boolean confirmada = false;

    @ManyToOne
    private User user;

    // Construtor vazio necessário para JPA
    public Matricula() {}

    // Construtor personalizado
    public Matricula(User user, Curso curso) {
        this.user = user;
        this.curso = curso;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public boolean isConfirmada() { return confirmada; } // getter
    public void setConfirmada(boolean confirmada) { this.confirmada = confirmada; } // setter
}






