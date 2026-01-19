package com.carlivan.smartshape.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record RotinaRequestDTO(
        @NotBlank String identificador,
        String nome,
        @NotEmpty @Valid List<ItemTreinoRequestDTO> itens
) {}
