package tn.esprit.artifact.service;

import tn.esprit.artifact.entity.JobPosition;

import java.util.List;
import java.util.Optional;

public interface IJobPositionService {
    JobPosition createJobPosition(JobPosition JobPosition);

    JobPosition updateJobPosition(Long id, JobPosition JobPosition);

    List<JobPosition> getAllJobPositions();

    JobPosition getJobPositionById(Long id);

    JobPosition deleteJobPosition(Long id);
}
