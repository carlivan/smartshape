package com.carlivan.smartshape.api.service;

import com.carlivan.smartshape.api.dto.PersonalRequestDTO;
import com.carlivan.smartshape.api.dto.PersonalResponseDTO;
import com.carlivan.smartshape.api.model.Personal;
import com.carlivan.smartshape.api.repository.PersonalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PersonalService {
    private final PersonalRepository personalRepository;

    public PersonalService(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    public PersonalResponseDTO salvar(PersonalRequestDTO dto){
        Personal personal = new Personal();
        personal.setNome(dto.nome());
        personal.setEmail(dto.email());
        personal.setCref(dto.cref());
        personal.setEspecialidade(dto.especialidade());

        Personal personalSalvo = personalRepository.save(personal);

        return new PersonalResponseDTO(
                personalSalvo.getId(),
                personalSalvo.getNome(),
                personalSalvo.getEmail()
        );
    }

    public List<PersonalResponseDTO> listarTodos(){
        return personalRepository.findAll()
                .stream()
                .map(p -> new PersonalResponseDTO(
                        p.getId(),
                        p.getNome(),
                        p.getEmail())
                )
                .toList();
    }

    public PersonalResponseDTO buscarPorId(UUID id){
        Personal personal = personalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal não encontrado"));

        return new PersonalResponseDTO(personal.getId(), personal.getNome(), personal.getEmail());
    }
}
