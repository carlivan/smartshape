package com.carlivan.smartshape.dto.response;

import java.util.UUID;

public record AlunoResumoDTO(
        UUID id,
        String nome,
        String objetivo
) {
}
