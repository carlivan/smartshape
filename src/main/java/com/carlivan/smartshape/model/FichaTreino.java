package com.carlivan.smartshape.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "ficha_treino")
public class FichaTreino {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome; // Ex: "Treino inicial"

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Enumerated(EnumType.STRING)
    private StatusFicha status; // ATIVO, ARQUIVADO

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @OneToMany(mappedBy = "fichaTreino", cascade = CascadeType.ALL)
    private Set<RotinaTreino> rotinas = new LinkedHashSet<>(); // Aqui ficam os treinos A, B, C

    public FichaTreino() {
    }

    public FichaTreino(String nome, LocalDate dataInicio, LocalDate dataFim, StatusFicha status, Aluno aluno, Set<RotinaTreino> rotinas) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.aluno = aluno;
        this.rotinas = rotinas;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public StatusFicha getStatus() {
        return status;
    }

    public void setStatus(StatusFicha status) {
        this.status = status;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Set<RotinaTreino> getRotinas() {
        return rotinas;
    }

    public void setRotinas(Set<RotinaTreino> rotinas) {
        this.rotinas = rotinas;
    }
}
