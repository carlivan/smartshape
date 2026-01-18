package com.carlivan.smartshape.dto.response;

import java.util.UUID;

public record AlunoResponseDTO(
        UUID id,
        String nome,
        String email,
        PersonalResponseDTO personal
) {
}
