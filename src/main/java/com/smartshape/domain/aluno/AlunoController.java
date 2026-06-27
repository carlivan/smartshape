package com.smartshape.domain.aluno;

import com.smartshape.domain.aluno.dto.AlunoResponse;
import com.smartshape.domain.aluno.dto.CadastrarAlunoRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoResponse> cadastrar(
            @Valid @RequestBody CadastrarAlunoRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(alunoService.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> listarTodos() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(alunoService.buscarPorId(id));
    }
}
