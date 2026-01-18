package com.carlivan.smartshape.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "item_treino")
public class ItemTreino {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "rotina_id")
    private RotinaTreino rotinaTreino;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercicio_id", nullable = false)
    private Exercicio exercicio;

    private Integer series;
    private String repeticoes;
    private String carga;
    private Integer descanso;
    private Integer ordem;
    private String observacoes;

    public ItemTreino() {
    }

    public ItemTreino(RotinaTreino rotinaTreino, Exercicio exercicio, Integer series, String repeticoes, String carga, Integer descanso, Integer ordem, String observacoes) {
        this.rotinaTreino = rotinaTreino;
        this.exercicio = exercicio;
        this.series = series;
        this.repeticoes = repeticoes;
        this.carga = carga;
        this.descanso = descanso;
        this.ordem = ordem;
        this.observacoes = observacoes;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RotinaTreino getRotinaTreino() {
        return rotinaTreino;
    }

    public void setRotinaTreino(RotinaTreino rotinaTreino) {
        this.rotinaTreino = rotinaTreino;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public String getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(String repeticoes) {
        this.repeticoes = repeticoes;
    }

    public String getCarga() {
        return carga;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }

    public Integer getDescanso() {
        return descanso;
    }

    public void setDescanso(Integer descanso) {
        this.descanso = descanso;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
