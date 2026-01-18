package com.carlivan.smartshape.repository;

import com.carlivan.smartshape.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, UUID> {

    List<Aluno> findByPersonalId(UUID personalId);
}
