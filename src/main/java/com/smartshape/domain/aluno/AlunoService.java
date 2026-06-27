package com.smartshape.domain.aluno;

import com.smartshape.domain.aluno.dto.AlunoResponse;
import com.smartshape.domain.aluno.dto.CadastrarAlunoRequest;
import com.smartshape.domain.user.User;
import com.smartshape.domain.user.UserRepository;
import com.smartshape.domain.user.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final UserRepository userRepository;

    @Transactional
    public AlunoResponse cadastrar(CadastrarAlunoRequest request) {
        log.info("Cadastrando novo aluno com email={}", request.email());

        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password()) // vamos criptografar na próxima etapa
                .role(UserRole.ALUNO)
                .active(true)
                .build();

        userRepository.save(user);

        Aluno aluno = Aluno.builder()
                .user(user)
                .matricula(gerarMatricula())
                .objetivo(request.objetivo())
                .telefone(request.telefone())
                .build();

        alunoRepository.save(aluno);

        log.info("Aluno cadastrado com sucesso. matricula={}", aluno.getMatricula());
        return AlunoResponse.from(aluno);
    }

    public List<AlunoResponse> listarTodos() {
        return alunoRepository.findAll()
                .stream()
                .map(AlunoResponse::from)
                .toList();
    }

    public AlunoResponse buscarPorId(UUID id) {
        return alunoRepository.findById(id)
                .map(AlunoResponse::from)
                .orElseThrow(() -> {
                    log.error("Aluno não encontrado. id={}", id);
                    return new RuntimeException("Aluno não encontrado");
                });
    }

    private String gerarMatricula() {
        return "SM" + UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();
    }
}