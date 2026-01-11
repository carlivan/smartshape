package com.carlivan.smartshape.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PersonalRequestDTO(
        @NotBlank String nome,
        @Email String email,
        @NotBlank String cref,
        String especialidade
) {
}
