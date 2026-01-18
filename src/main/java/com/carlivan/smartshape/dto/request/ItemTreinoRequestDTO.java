package com.carlivan.smartshape.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ItemTreinoRequestDTO(
        @NotNull UUID exercicioId,
        @NotNull Integer series,
        @NotBlank String repeticoes,
        String carga,
        Integer descanso,
        @NotNull Integer ordem,
        String observacoes
        ) {
}
