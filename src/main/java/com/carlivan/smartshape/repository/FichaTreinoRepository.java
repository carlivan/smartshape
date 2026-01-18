package com.carlivan.smartshape.repository;

import com.carlivan.smartshape.model.FichaTreino;
import com.carlivan.smartshape.model.StatusFicha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FichaTreinoRepository extends JpaRepository<FichaTreino, UUID> {

    @Query("SELECT DISTINCT f FROM FichaTreino f LEFT JOIN FETCH f.rotinas r " +
            "LEFT JOIN FETCH r.itens i LEFT JOIN FETCH i.exercicio e " +
            "WHERE f.aluno.id = :alunoId AND f.status = :status")
    Optional<FichaTreino> findByAlunoIdAndStatusFetch(UUID alunoId, StatusFicha status);

    @Query("SELECT DISTINCT f FROM FichaTreino f " +
            "LEFT JOIN FETCH f.rotinas r " +
            "LEFT JOIN FETCH r.itens i " +
            "LEFT JOIN FETCH i.exercicio e")
    List<FichaTreino> findAllFetch();
}
