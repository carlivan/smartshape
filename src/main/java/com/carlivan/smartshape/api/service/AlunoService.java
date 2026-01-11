package com.carlivan.smartshape.api.service;

import com.carlivan.smartshape.api.dto.request.AlunoRequestDTO;
import com.carlivan.smartshape.api.dto.response.AlunoResponseDTO;
import com.carlivan.smartshape.api.dto.response.PersonalResponseDTO;
import com.carlivan.smartshape.api.model.Aluno;
import com.carlivan.smartshape.api.model.Personal;
import com.carlivan.smartshape.api.repository.AlunoRepository;
import com.carlivan.smartshape.api.repository.PersonalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final PersonalRepository personalRepository;

    public AlunoService(AlunoRepository alunoRepository, PersonalRepository personalRepository) {
        this.alunoRepository = alunoRepository;
        this.personalRepository = personalRepository;
    }

    public AlunoResponseDTO salvar(AlunoRequestDTO alunoRequestDTO){
        Aluno aluno = new Aluno();
        aluno.setNome(alunoRequestDTO.nome());
        aluno.setEmail(alunoRequestDTO.email());
        aluno.setDataNascimento(alunoRequestDTO.dataNascimento());
        aluno.setObjetivo(alunoRequestDTO.objetivo());

        PersonalResponseDTO personalResponseDTO = null;

        if (alunoRequestDTO.personalId() != null){
            Personal personal = personalRepository.findById(alunoRequestDTO.personalId())
                    .orElseThrow(() -> new EntityNotFoundException("Personal não econtrado!"));
            aluno.setPersonal(personal);

            personalResponseDTO = new PersonalResponseDTO(
                    personal.getId(),
                    personal.getNome(),
                    personal.getEmail()
            );
        }

        Aluno alunoSalvo = alunoRepository.save(aluno);

        return new AlunoResponseDTO(
                alunoSalvo.getId(),
                alunoSalvo.getNome(),
                alunoSalvo.getEmail(),
                personalResponseDTO
        );
    }
}
