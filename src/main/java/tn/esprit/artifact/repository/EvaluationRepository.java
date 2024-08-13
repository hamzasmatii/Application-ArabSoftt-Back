package tn.esprit.artifact.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.artifact.entity.Competence;
import tn.esprit.artifact.entity.Evaluation;
import tn.esprit.artifact.entity.EvaluationType;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation,Long>{
    List<Evaluation> findByUserIdAndEval(Long userId, EvaluationType eval);

    List<Evaluation> findByUserIdAndCompetenceIdAndEval(Long userId, Long competenceId, EvaluationType eval);
    List<Evaluation> findByUserIdAndCompetenceId(Long userId, Long competenceId);


}
