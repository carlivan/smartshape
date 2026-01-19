package com.carlivan.smartshape.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record ExercicioRequestDTO(
        @NotBlank String nome,
        @NotBlank String grupoMuscular,
        String descricao,
        @URL(message = "A URL de vídeo deve ser válida")
        String videoUrl
) {}
