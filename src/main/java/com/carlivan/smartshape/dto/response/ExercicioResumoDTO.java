package com.carlivan.smartshape.dto.response;

import java.util.UUID;

public record ExercicioResumoDTO(
        UUID id,
        String nome,
        String grupoMuscular
) {
}
