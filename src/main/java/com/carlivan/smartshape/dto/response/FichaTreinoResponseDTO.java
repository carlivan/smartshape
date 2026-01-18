package com.carlivan.smartshape.dto.response;

import com.carlivan.smartshape.model.StatusFicha;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

// O nível mestre: A Ficha com tudo dentro
public record FichaTreinoResponseDTO(
        UUID id,
        String nome,
        String alunoNome,
        LocalDate dataInicio,
        StatusFicha status,
        List<RotinaResponseDTO> rotinas
) {
}
