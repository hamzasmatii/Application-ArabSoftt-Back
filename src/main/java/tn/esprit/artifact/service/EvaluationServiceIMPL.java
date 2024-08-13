package tn.esprit.artifact.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.artifact.entity.Evaluation;
import tn.esprit.artifact.entity.EvaluationType;
import tn.esprit.artifact.repository.EvaluationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class EvaluationServiceIMPL implements IEvaluationService{
    @Autowired
    private EvaluationRepository evaluationRepository;

    @Override
    public Evaluation createEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    @Override
    public Evaluation updateEvaluation(Long id, Evaluation evaluation) {
        Optional<Evaluation> optionalEvaluation = evaluationRepository.findById(id);

        if (optionalEvaluation.isPresent()) {
            Evaluation existingEvaluation = optionalEvaluation.get();

            // Update fields only if they are not null
            if (evaluation.getEval() != null) {
                existingEvaluation.setEval(evaluation.getEval());
                existingEvaluation.calculNote(); // Recalculate the note based on the new eval
            }
            if (evaluation.getCompetence() != null) {
                existingEvaluation.setCompetence(evaluation.getCompetence());
            }


            // Save the updated evaluation entity
            return evaluationRepository.save(existingEvaluation);
        } else {
            // Handle the case where the evaluation with the given ID is not found
            throw new IllegalArgumentException("Evaluation not found with ID: " + id);
        }
    }


    @Override
    public List<Evaluation> getAllEvaluations() {
        Iterable<Evaluation> evaluationsIterable = evaluationRepository.findAll();
        List<Evaluation> evaluationsList = new ArrayList<>();
        for (Evaluation evaluation : evaluationsIterable) {
            evaluationsList.add(evaluation);
        }
        return evaluationsList;
    }

    @Override
    public Evaluation getEvaluationById(Long id) {
        return evaluationRepository.findById(id).orElse(null);
    }

    @Override
    public Evaluation deleteEvaluation(Long id) {
        try{
            Optional<Evaluation> optionalEvaluation = evaluationRepository.findById(id);



            // If the evaluation exists, retrieve it
            Evaluation evaluationToDelete = optionalEvaluation.get();

            // Delete the evaluation by its ID
            evaluationRepository.deleteById(id);

            // Return the deleted stage
            return evaluationToDelete;
        } catch(Exception e) {
            // If the stage does not exist, throw an exception or handle it in any other appropriate way
            throw new IllegalArgumentException("evaluation not found");
        }

    }

    @Override
    public List<Evaluation> findByUserIdAndCompetenceIdAndEval(Long userId, Long competenceId, EvaluationType eval) {
        return evaluationRepository.findByUserIdAndCompetenceIdAndEval(userId, competenceId, eval);
    }

    @Override
    public List<Evaluation> findByUserIdAndCompetenceId(Long userId, Long competenceId) {
        return evaluationRepository.findByUserIdAndCompetenceId(userId, competenceId);
    }

}
