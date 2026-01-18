package com.carlivan.smartshape.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "exercicio")
public class Exercicio {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "grupo_muscular", nullable = false)
    private String grupoMuscular;

    private String descricao;

    @Column(name = "video_url")
    private String videoUrl;

    public Exercicio() {
    }

    public Exercicio(String nome, String grupoMuscular, String descricao, String videoUrl) {
        this.nome = nome;
        this.grupoMuscular = grupoMuscular;
        this.descricao = descricao;
        this.videoUrl = videoUrl;
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

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
