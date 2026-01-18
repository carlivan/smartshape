package com.carlivan.smartshape.service;

import com.carlivan.smartshape.dto.request.FichaTreinoRequestDTO;
import com.carlivan.smartshape.dto.request.ItemTreinoRequestDTO;
import com.carlivan.smartshape.dto.request.RotinaRequestDTO;
import com.carlivan.smartshape.dto.response.FichaTreinoResponseDTO;
import com.carlivan.smartshape.dto.response.ItemTreinoResponseDTO;
import com.carlivan.smartshape.dto.response.RotinaResponseDTO;
import com.carlivan.smartshape.model.*;
import com.carlivan.smartshape.repository.AlunoRepository;
import com.carlivan.smartshape.repository.ExercicioRepository;
import com.carlivan.smartshape.repository.FichaTreinoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FichaTreinoService {
    private final FichaTreinoRepository fichaTreinoRepository;
    private final AlunoRepository alunoRepository;
    private final ExercicioRepository exercicioRepository;

    public FichaTreinoService(FichaTreinoRepository fichaTreinoRepository, AlunoRepository alunoRepository, ExercicioRepository exercicioRepository) {
        this.fichaTreinoRepository = fichaTreinoRepository;
        this.alunoRepository = alunoRepository;
        this.exercicioRepository = exercicioRepository;
    }

    @Transactional
    public FichaTreinoResponseDTO salvar(FichaTreinoRequestDTO requestDTO){
        // Buscar o Aluno
        Aluno aluno = alunoRepository.findById(requestDTO.alunoId())
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado!"));

        // Criar a ficha
        FichaTreino ficha = new FichaTreino();
        ficha.setNome(requestDTO.nome());
        ficha.setDataInicio(requestDTO.dataInicio());
        ficha.setStatus(StatusFicha.ATIVO);
        ficha.setAluno(aluno);

        // Converte as rotinas
        for (RotinaRequestDTO rotinaDTO : requestDTO.rotinas()){
            RotinaTreino rotina = new RotinaTreino();
            rotina.setIdentificador(rotinaDTO.identificador());
            rotina.setNome(rotinaDTO.nome());
            rotina.setFichaTreino(ficha);

            // Converte os Itens (Exercícios)
            for (ItemTreinoRequestDTO itemDTO : rotinaDTO.itens()){
                ItemTreino item = new ItemTreino();

                // Busca o exercício do catálogo
                Exercicio exercicio = exercicioRepository.findById(itemDTO.exercicioId())
                        .orElseThrow(() -> new EntityNotFoundException("Exercício não encontrado!"));

                item.setExercicio(exercicio);
                item.setSeries(itemDTO.series());
                item.setRepeticoes(itemDTO.repeticoes());
                item.setCarga(itemDTO.carga());
                item.setDescanso(itemDTO.descanso());
                item.setOrdem(itemDTO.ordem());
                item.setRotinaTreino(rotina);
                item.setObservacoes(itemDTO.observacoes());

                rotina.getItens().add(item);
            }
            ficha.getRotinas().add(rotina);
        }
        ;
        return toResponseDTO(fichaTreinoRepository.save(ficha));
    }

    public List<FichaTreinoResponseDTO> listarFichas(){

        return fichaTreinoRepository.findAllFetch()
                .stream()
                .map(this::toResponseDTO).toList();
    }

    @Transactional(readOnly = true)
    public FichaTreinoResponseDTO buscarFichaAtivaDoAluno(UUID alunoId){
        FichaTreino ficha = fichaTreinoRepository.findByAlunoIdAndStatusFetch(alunoId, StatusFicha.ATIVO)
                .orElseThrow(() -> new EntityNotFoundException("O aluno não possui treino ativo"));
        return toResponseDTO(ficha);
    }

    private FichaTreinoResponseDTO toResponseDTO(FichaTreino ficha) {
        List<RotinaResponseDTO> rotinasDTO = ficha.getRotinas().stream()
                .map(rotina -> new RotinaResponseDTO(
                        rotina.getId(),
                        rotina.getIdentificador(),
                        rotina.getNome(),
                        rotina.getItens().stream()
                                .map(item -> new ItemTreinoResponseDTO(
                                        item.getId(),
                                        item.getExercicio().getNome(),
                                        item.getExercicio().getGrupoMuscular(),
                                        item.getSeries(),
                                        item.getRepeticoes(),
                                        item.getCarga(),
                                        item.getDescanso(),
                                        item.getOrdem(),
                                        item.getObservacoes(),
                                        item.getExercicio().getVideoUrl()
                                )).toList()
                )).toList();

        return new FichaTreinoResponseDTO(
                ficha.getId(),
                ficha.getNome(),
                ficha.getAluno().getNome(),
                ficha.getDataInicio(),
                ficha.getStatus(),
                rotinasDTO
        );
    }

}
