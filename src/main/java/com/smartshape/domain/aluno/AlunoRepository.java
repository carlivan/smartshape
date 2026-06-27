package com.smartshape.domain.aluno;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AlunoRepository extends JpaRepository<Aluno, UUID> {
    Optional<Aluno> findByMatricula(String matricula);
    Optional<Aluno> findByUserId(UUID userId);
    boolean existsByMatricula(String matricula);
}
