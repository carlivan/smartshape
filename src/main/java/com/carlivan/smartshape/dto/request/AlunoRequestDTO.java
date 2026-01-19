package com.carlivan.smartshape.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AlunoRequestDTO(
        @NotBlank String nome,
        @Email String email,
        @NotNull LocalDate dataNascimento,
        String objetivo,
        UUID personalId // Pode ser nulo caso o aluno treine sozinho
        ) {}
