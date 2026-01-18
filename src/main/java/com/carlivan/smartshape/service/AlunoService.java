package com.carlivan.smartshape.service;

import com.carlivan.smartshape.dto.request.AlunoRequestDTO;
import com.carlivan.smartshape.dto.response.AlunoResponseDTO;
import com.carlivan.smartshape.dto.response.AlunoResumoDTO;
import com.carlivan.smartshape.dto.response.PersonalResponseDTO;
import com.carlivan.smartshape.model.Aluno;
import com.carlivan.smartshape.model.Personal;
import com.carlivan.smartshape.repository.AlunoRepository;
import com.carlivan.smartshape.repository.PersonalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final PersonalRepository personalRepository;

    public AlunoService(AlunoRepository alunoRepository, PersonalRepository personalRepository) {
        this.alunoRepository = alunoRepository;
        this.personalRepository = personalRepository;
    }

    @Transactional
    public AlunoResponseDTO salvar(AlunoRequestDTO alunoRequestDTO){
        Aluno aluno = new Aluno();
        aluno.setNome(alunoRequestDTO.nome());
        aluno.setEmail(alunoRequestDTO.email());
        aluno.setDataNascimento(alunoRequestDTO.dataNascimento());
        aluno.setObjetivo(alunoRequestDTO.objetivo());

        if (alunoRequestDTO.personalId() != null){
            Personal personal = personalRepository.findById(alunoRequestDTO.personalId())
                    .orElseThrow(() -> new EntityNotFoundException("Personal não econtrado!"));
            aluno.setPersonal(personal);
        }

        Aluno alunoSalvo = alunoRepository.save(aluno);

        return toResponseDTO(alunoSalvo);
    }

    public AlunoResponseDTO buscarPorId(UUID id){
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado!"));

        return toResponseDTO(aluno);
    }

    public List<AlunoResumoDTO> buscarAlunosPorPersonal(UUID personalId){
        if(!personalRepository.existsById(personalId)){
            throw new EntityNotFoundException("Personal não encontrado!");
        }

        return alunoRepository.findByPersonalId(personalId)
                .stream()
                .map(aluno -> new AlunoResumoDTO(
                        aluno.getId(),
                        aluno.getNome(),
                        aluno.getEmail()))
                .toList();
    }

    public List<AlunoResponseDTO> listarTodos(){
        return alunoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional
    public AlunoResponseDTO atualizar(UUID id, AlunoRequestDTO alunoRequestDTO){
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado!"));
        if (aluno.getNome() != null && !aluno.getNome().equals(alunoRequestDTO.nome())){
            aluno.setNome(alunoRequestDTO.nome());
        }
        if (aluno.getEmail() != null && !aluno.getEmail().equals(alunoRequestDTO.email())){
            aluno.setEmail(alunoRequestDTO.email());
        }
        if (aluno.getDataNascimento() != null && !aluno.getDataNascimento().equals(alunoRequestDTO.dataNascimento())){
            aluno.setDataNascimento(alunoRequestDTO.dataNascimento());
        }
        if (aluno.getObjetivo() != null && !aluno.getObjetivo().equals(alunoRequestDTO.objetivo())){
            aluno.setObjetivo(alunoRequestDTO.objetivo());
        }
        if (alunoRequestDTO.personalId() != null ){
            Personal personal = personalRepository.findById(alunoRequestDTO.personalId())
                    .orElseThrow(() -> new EntityNotFoundException("Personal não encontrado!!"));
            aluno.setPersonal(personal);
        }else {
            aluno.setPersonal(null);
        }

        return toResponseDTO(aluno);
    }

    @Transactional
    public void deletar(UUID id){
        if (!alunoRepository.existsById(id)){
            throw new EntityNotFoundException("Aluno não encontrado!");
        }
        alunoRepository.deleteById(id);
    }

    private AlunoResponseDTO toResponseDTO(Aluno aluno){
        PersonalResponseDTO personalResponseDTO = null;

        if (aluno.getPersonal() != null){
            personalResponseDTO = new PersonalResponseDTO(
                    aluno.getPersonal().getId(),
                    aluno.getPersonal().getNome(),
                    aluno.getPersonal().getEmail()
            );
        }

        return new AlunoResponseDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                personalResponseDTO
        );
    }
}
