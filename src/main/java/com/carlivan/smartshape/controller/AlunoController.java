package com.carlivan.smartshape.controller;

import com.carlivan.smartshape.dto.request.AlunoRequestDTO;
import com.carlivan.smartshape.dto.response.AlunoResponseDTO;
import com.carlivan.smartshape.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> salvar(@Valid @RequestBody AlunoRequestDTO alunoRequestDTO){
        AlunoResponseDTO alunoResponseDTO = alunoService.salvar(alunoRequestDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(alunoResponseDTO.id())
                .toUri();

        return ResponseEntity.created(uri).body(alunoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> listarTodos(){
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> buscarPorId(@PathVariable UUID id){
        return ResponseEntity.ok(alunoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizar(@PathVariable UUID id, @Valid @RequestBody AlunoRequestDTO alunoRequestDTO){
        return ResponseEntity.ok(alunoService.atualizar(id, alunoRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
