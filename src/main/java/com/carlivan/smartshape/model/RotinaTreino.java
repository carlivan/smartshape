package com.carlivan.smartshape.model;

import jakarta.persistence.*;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.util.*;

@Entity
@Table(name = "rotina_treino")
public class RotinaTreino {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome; // ex: "Costas e Biceps"

    private String identificador; // "A", "B", "C"

    @ManyToOne
    @JoinColumn(name = "ficha_id")
    private FichaTreino fichaTreino;

    @OneToMany(mappedBy = "rotinaTreino", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("ordem ASC")
    private Set<ItemTreino> itens = new LinkedHashSet<>();

    public RotinaTreino() {
    }

    public RotinaTreino(String nome, String identificador, FichaTreino fichaTreino, Set<ItemTreino> itens) {
        this.nome = nome;
        this.identificador = identificador;
        this.fichaTreino = fichaTreino;
        this.itens = itens;
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

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public FichaTreino getFichaTreino() {
        return fichaTreino;
    }

    public void setFichaTreino(FichaTreino fichaTreino) {
        this.fichaTreino = fichaTreino;
    }

    public Set<ItemTreino> getItens() {
        return itens;
    }

    public void setItens(Set<ItemTreino> itens) {
        this.itens = itens;
    }
}
