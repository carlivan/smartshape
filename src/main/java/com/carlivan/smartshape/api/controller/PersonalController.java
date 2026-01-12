package com.carlivan.smartshape.api.controller;

import com.carlivan.smartshape.api.dto.request.PersonalRequestDTO;
import com.carlivan.smartshape.api.dto.response.AlunoResumoDTO;
import com.carlivan.smartshape.api.dto.response.PersonalResponseDTO;
import com.carlivan.smartshape.api.service.AlunoService;
import com.carlivan.smartshape.api.service.PersonalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/personais")
public class PersonalController {
    private final PersonalService personalService;
    private final AlunoService alunoService;

    public PersonalController(PersonalService personalService, AlunoService alunoService) {
        this.personalService = personalService;
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<PersonalResponseDTO> salvar(@Valid @RequestBody PersonalRequestDTO requestDTO){
        PersonalResponseDTO responseDTO = personalService.salvar(requestDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.id())
                .toUri();

        return ResponseEntity.created(uri)
                .body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PersonalResponseDTO>> listarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(personalService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalResponseDTO> buscarPorId(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(personalService.buscarPorId(id));
    }

    @GetMapping("{id}/alunos")
    public ResponseEntity<List<AlunoResumoDTO>> listarAlunos(@PathVariable UUID id){
        return ResponseEntity.ok(alunoService.buscarAlunosPorPersonal(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalResponseDTO> atualizar(@PathVariable UUID id, @Valid @RequestBody PersonalRequestDTO requestDTO){
        return ResponseEntity.ok(personalService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        personalService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
