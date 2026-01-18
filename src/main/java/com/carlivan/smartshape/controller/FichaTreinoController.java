package com.carlivan.smartshape.controller;

import com.carlivan.smartshape.dto.request.FichaTreinoRequestDTO;
import com.carlivan.smartshape.dto.response.FichaTreinoResponseDTO;
import com.carlivan.smartshape.service.FichaTreinoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fichas")
public class FichaTreinoController {
    private final FichaTreinoService treinoService;

    public FichaTreinoController(FichaTreinoService treinoService) {
        this.treinoService = treinoService;
    }

    @PostMapping
    public ResponseEntity<FichaTreinoResponseDTO> salvar(@RequestBody FichaTreinoRequestDTO requestDTO){
        FichaTreinoResponseDTO responseDTO = treinoService.salvar(requestDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.id())
                .toUri();

        return ResponseEntity.created(uri).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<FichaTreinoResponseDTO>> listarFichas(){
        return ResponseEntity.ok(treinoService.listarFichas());
    }

    @GetMapping("/aluno/{alunoId}/ativa")
    public ResponseEntity<FichaTreinoResponseDTO> getFichaAtiva(@PathVariable UUID alunoId){
        var response = treinoService.buscarFichaAtivaDoAluno(alunoId);
        return ResponseEntity.ok(response);
    }
}
