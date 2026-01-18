package com.carlivan.smartshape.dto.response;

import java.util.List;
import java.util.UUID;

// O nível médio: A Rotina (A, B, C) com seus exercícios
public record RotinaResponseDTO(
        UUID id,
        String identificador,
        String nome,
        List<ItemTreinoResponseDTO> itens
) {
}
