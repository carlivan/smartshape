package com.carlivan.smartshape.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record RotinaRequestDTO(
        @NotBlank String identificador,
        String nome,
        List<ItemTreinoRequestDTO> itens
) {
}
