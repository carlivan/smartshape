package com.smartshape.domain.aluno.dto;

import com.smartshape.domain.aluno.Aluno;

import java.util.UUID;

public record AlunoResponse(
        UUID id,
        String name,
        String email,
        String matricula,
        String objetivo,
        String telefone
) {
    public static AlunoResponse from(Aluno aluno) {
        return new AlunoResponse(
                aluno.getId(),
                aluno.getUser().getName(),
                aluno.getUser().getEmail(),
                aluno.getMatricula(),
                aluno.getObjetivo(),
                aluno.getTelefone()
        );
    }
}
