package com.carlivan.smartshape.dto.response;

import java.util.UUID;
// O nível mais profundo: O exercício detalhado dentro do treino
public record ItemTreinoResponseDTO(
        UUID id,
        String nomeExercicio,
        String grupoMuscular,
        Integer series,
        String repeticoes,
        String carga,
        Integer descanso,
        Integer ordem,
        String observacoes,
        String videoUrl
) {
}
