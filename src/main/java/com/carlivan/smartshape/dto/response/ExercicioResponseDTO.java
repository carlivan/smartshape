package com.carlivan.smartshape.dto.response;

import java.util.UUID;

public record ExercicioResponseDTO(
        UUID id,
        String nome,
        String grupoMuscular,
        String descricao,
        String videoUrl
) {
}
