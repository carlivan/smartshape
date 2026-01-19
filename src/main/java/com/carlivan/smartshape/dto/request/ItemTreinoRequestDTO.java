package com.carlivan.smartshape.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record ItemTreinoRequestDTO(
        @NotNull UUID exercicioId,
        @Positive @NotNull Integer series,
        @NotBlank String repeticoes,
        String carga,
        @PositiveOrZero Integer descanso,
        @NotNull Integer ordem,
        String observacoes
        ) {}
