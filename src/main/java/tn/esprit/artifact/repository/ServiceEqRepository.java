package tn.esprit.artifact.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.artifact.entity.ServiceEq;

@Repository

public interface ServiceEqRepository extends JpaRepository<ServiceEq,Long> {
}
