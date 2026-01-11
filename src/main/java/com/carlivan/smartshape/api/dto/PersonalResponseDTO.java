package com.carlivan.smartshape.api.dto;

import java.util.UUID;

public record PersonalResponseDTO(
        UUID id,
        String nome,
        String email
) {
}
