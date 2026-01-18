package com.carlivan.smartshape.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ExercicioRequestDTO(
        @NotBlank String nome,
        @NotBlank String grupoMuscular,
        String descricao,
        String videoUrl
) {
}
