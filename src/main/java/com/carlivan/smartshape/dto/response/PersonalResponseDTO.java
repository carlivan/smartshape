package com.carlivan.smartshape.dto.response;

import java.util.UUID;

public record PersonalResponseDTO(
        UUID id,
        String nome,
        String email
) {
}
