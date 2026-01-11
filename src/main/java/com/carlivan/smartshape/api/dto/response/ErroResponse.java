package com.carlivan.smartshape.api.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record ErroResponse(
        LocalDateTime timesamp,
        Integer status,
        String erro,
        String mensagem,
        String path,
        List<CampoErro> campos
) {
    public record CampoErro(String campo, String mensagem){}
}
