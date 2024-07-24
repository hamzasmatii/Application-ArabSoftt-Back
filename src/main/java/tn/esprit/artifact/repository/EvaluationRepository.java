package tn.esprit.artifact.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.artifact.entity.Competence;
import tn.esprit.artifact.entity.Evaluation;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation,Long>{
}
