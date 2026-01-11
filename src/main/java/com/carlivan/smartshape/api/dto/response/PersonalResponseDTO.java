package com.carlivan.smartshape.api.dto.response;

import java.util.UUID;

public record PersonalResponseDTO(
        UUID id,
        String nome,
        String email
) {
}
