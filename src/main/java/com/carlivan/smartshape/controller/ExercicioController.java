package com.carlivan.smartshape.controller;

import com.carlivan.smartshape.dto.request.ExercicioRequestDTO;
import com.carlivan.smartshape.dto.response.ExercicioResponseDTO;
import com.carlivan.smartshape.dto.response.ExercicioResumoDTO;
import com.carlivan.smartshape.service.ExercicioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exercicios")
public class ExercicioController {
    private final ExercicioService exercicioService;

    public ExercicioController(ExercicioService exercicioService) {
        this.exercicioService = exercicioService;
    }

    @PostMapping
    public ResponseEntity<ExercicioResponseDTO> salvarExercicio(@Valid @RequestBody ExercicioRequestDTO requestDTO){
        ExercicioResponseDTO responseDTO = exercicioService.salvarExercicio(requestDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.id())
                .toUri();

        return ResponseEntity.created(uri).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ExercicioResumoDTO>> listarExercicios(){
        return ResponseEntity.ok(exercicioService.listarExercicios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExercicioResponseDTO> buscarUm(@PathVariable UUID id){
        return ResponseEntity.ok(exercicioService.buscarUm(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExercicioResponseDTO> atualizarExercicio(@PathVariable UUID exercicioId,
                                                                   @Valid @RequestBody ExercicioRequestDTO requestDTO){
        return ResponseEntity.ok(exercicioService.atualizar(exercicioId, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarExercicio(@PathVariable UUID id){
        exercicioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
