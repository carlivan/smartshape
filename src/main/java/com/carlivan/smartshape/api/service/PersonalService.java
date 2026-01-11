package com.carlivan.smartshape.api.service;

import com.carlivan.smartshape.api.dto.request.PersonalRequestDTO;
import com.carlivan.smartshape.api.dto.response.PersonalResponseDTO;
import com.carlivan.smartshape.api.model.Personal;
import com.carlivan.smartshape.api.repository.PersonalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonalService {
    private final PersonalRepository personalRepository;

    public PersonalService(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    @Transactional
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
                .orElseThrow(() -> new EntityNotFoundException("Personal não encontrado!"));

        return new PersonalResponseDTO(personal.getId(), personal.getNome(), personal.getEmail());
    }

    @Transactional
    public PersonalResponseDTO atualizar(UUID id, PersonalRequestDTO requestDTO){
        Personal personal = personalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Personal não encontrado!"));

        if (personal.getNome() != null && !personal.getNome().equals(requestDTO.nome())){
            personal.setNome(requestDTO.nome());
        }
        if (personal.getEmail() != null && !personal.getEmail().equals(requestDTO.email())){
            personal.setEmail(requestDTO.email());
        }
        if (personal.getCref() != null && !personal.getCref().equals(requestDTO.cref())){
            personal.setCref(requestDTO.cref());
        }
        if (personal.getEspecialidade() != null && !personal.getEspecialidade().equals(requestDTO.especialidade())){
            personal.setEspecialidade(requestDTO.especialidade());
        }

        Personal atualizado = personalRepository.save(personal);

        return new PersonalResponseDTO(atualizado.getId(), atualizado.getNome(), atualizado.getEmail());
    }

    @Transactional
    public void deletar(UUID id){
        Personal personal = personalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Personal não encontrado"));
        personalRepository.delete(personal);
    }
}
