package tn.esprit.artifact.service;

import tn.esprit.artifact.entity.Evaluation;

import java.util.List;

public interface IEvaluationService {
    Evaluation createEvaluation(Evaluation Evaluation);

    Evaluation updateEvaluation(Long id, Evaluation Evaluation);

    List<Evaluation> getAllEvaluations();

    Evaluation getEvaluationById(Long id);

    Evaluation deleteEvaluation(Long id);
}
