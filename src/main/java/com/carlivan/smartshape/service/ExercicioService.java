package com.carlivan.smartshape.service;

import com.carlivan.smartshape.dto.request.ExercicioRequestDTO;
import com.carlivan.smartshape.dto.response.ExercicioResponseDTO;
import com.carlivan.smartshape.dto.response.ExercicioResumoDTO;
import com.carlivan.smartshape.model.Exercicio;
import com.carlivan.smartshape.repository.ExercicioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ExercicioService {
    private final ExercicioRepository exercicioRepository;

    public ExercicioService(ExercicioRepository exercicioRepository) {
        this.exercicioRepository = exercicioRepository;
    }

    @Transactional
    public ExercicioResponseDTO salvarExercicio(ExercicioRequestDTO requestDTO){
        Exercicio exercicio = new Exercicio();
        exercicio.setNome(requestDTO.nome());
        exercicio.setGrupoMuscular(requestDTO.grupoMuscular());
        exercicio.setDescricao(requestDTO.descricao());
        exercicio.setVideoUrl(requestDTO.videoUrl());

        return toResponseDTO(exercicioRepository.save(exercicio));
    }

    @Transactional(readOnly = true)
    public List<ExercicioResumoDTO> listarExercicios(){
        List<ExercicioResumoDTO> exercicios = exercicioRepository.findAll()
                .stream()
                .map(exercicio -> new ExercicioResumoDTO(
                        exercicio.getId(),
                        exercicio.getNome(),
                        exercicio.getGrupoMuscular()
                )).toList();

        return exercicios;
    }

    public ExercicioResponseDTO buscarUm(UUID exercicioId){
        return exercicioRepository.findById(exercicioId)
                .map(exercicio -> new ExercicioResponseDTO(
                        exercicio.getId(),
                        exercicio.getNome(),
                        exercicio.getGrupoMuscular(),
                        exercicio.getDescricao(),
                        exercicio.getVideoUrl()
                )).orElseThrow(() -> new EntityNotFoundException("Exercicio não encontrado!"));
    }

    public ExercicioResponseDTO atualizar(UUID exercicioId, ExercicioRequestDTO requestDTO){
        Exercicio exercicio = exercicioRepository.findById(exercicioId)
                .orElseThrow(() -> new EntityNotFoundException("Exercicio não encontrado"));

        if (!Objects.equals(exercicio.getNome(), requestDTO.nome())){
            exercicio.setNome(requestDTO.nome());
        }
        if (!Objects.equals(exercicio.getGrupoMuscular(), requestDTO.grupoMuscular())){
            exercicio.setGrupoMuscular(requestDTO.grupoMuscular());
        }
        if (!exercicio.getDescricao().equals(requestDTO.descricao())){
            exercicio.setDescricao(requestDTO.descricao());
        }
        if (!exercicio.getVideoUrl().equals(requestDTO.videoUrl())){
            exercicio.setVideoUrl(requestDTO.videoUrl());
        }

        return toResponseDTO(exercicioRepository.save(exercicio));
    }

    public void delete(UUID exercicioId){
        Exercicio exercicio = exercicioRepository.findById(exercicioId)
                .orElseThrow(() -> new EntityNotFoundException("Exercício não encontrado"));
        exercicioRepository.delete(exercicio);
    }

    public ExercicioResponseDTO toResponseDTO(Exercicio exercicio){
        return new ExercicioResponseDTO(
                exercicio.getId(),
                exercicio.getNome(),
                exercicio.getGrupoMuscular(),
                exercicio.getDescricao(),
                exercicio.getVideoUrl()
        );
    }
}
