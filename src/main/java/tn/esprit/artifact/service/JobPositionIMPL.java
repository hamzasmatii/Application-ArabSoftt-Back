package tn.esprit.artifact.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.artifact.entity.JobPosition;
import tn.esprit.artifact.repository.JobPositionRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class JobPositionIMPL implements IJobPositionService{
    @Autowired
    private JobPositionRepository jobpositionRepository;

    @Override
    public JobPosition createJobPosition(JobPosition jobposition) {
        return jobpositionRepository.save(jobposition);
    }

    @Override
    public JobPosition updateJobPosition(Long id, JobPosition jobposition) {
        Optional<JobPosition> optionalJobPosition = jobpositionRepository.findById(id);

        if (optionalJobPosition.isPresent()) {
            JobPosition existingJobPosition = optionalJobPosition.get();

            // Update fields only if they are not null
            if (jobposition.getDescription() != null) {
                existingJobPosition.setDescription(jobposition.getDescription());
            }
            if (jobposition.getNom() != null) {
                existingJobPosition.setNom(jobposition.getNom());
            }

            if (jobposition.getCompetencesRequises() != null) {
                existingJobPosition.setCompetencesRequises(jobposition.getCompetencesRequises());
            } else {
                existingJobPosition.setCompetencesRequises(new HashSet<>()); // Or handle as appropriate if null should be explicitly set
            }


            // Save the updated job position entity
            return jobpositionRepository.save(existingJobPosition);
        } else {
            // Handle the case where the job position with the given ID is not found
            throw new IllegalArgumentException("JobPosition not found with ID: " + id);
        }
    }


    @Override
    public List<JobPosition> getAllJobPositions() {
        Iterable<JobPosition> jobpositionsIterable = jobpositionRepository.findAll();
        List<JobPosition> jobpositionsList = new ArrayList<>();
        for (JobPosition jobposition : jobpositionsIterable) {
            jobpositionsList.add(jobposition);
        }
        return jobpositionsList;
    }

    @Override
    public JobPosition getJobPositionById(Long id) {
        return jobpositionRepository.findById(id).orElse(null);
    }

    @Override
    public JobPosition deleteJobPosition(Long id) {
        try{
            Optional<JobPosition> optionalJobPosition = jobpositionRepository.findById(id);



            // If the jobposition exists, retrieve it
            JobPosition jobpositionToDelete = optionalJobPosition.get();

            // Delete the jobposition by its ID
            jobpositionRepository.deleteById(id);

            // Return the deleted stage
            return jobpositionToDelete;
        } catch(Exception e) {
            // If the stage does not exist, throw an exception or handle it in any other appropriate way
            throw new IllegalArgumentException("jobposition not found");
        }

    }
}
