package com.carlivan.smartshape.api.dto.response;

import java.util.UUID;

public record AlunoResponseDTO(
        UUID id,
        String nome,
        String email,
        PersonalResponseDTO personal
) {
}
