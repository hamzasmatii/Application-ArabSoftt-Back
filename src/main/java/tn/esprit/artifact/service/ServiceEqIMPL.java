package tn.esprit.artifact.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.artifact.entity.ServiceEq;
import tn.esprit.artifact.repository.ServiceEqRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ServiceEqIMPL implements IServiceEqService {
    @Autowired
    private ServiceEqRepository serviceEqRepository;

    @Override
    public ServiceEq createServiceEq(ServiceEq serviceEq) {
        return serviceEqRepository.save(serviceEq);
    }

    @Override
    public ServiceEq updateServiceEq(Long id, ServiceEq serviceEq) {
        Optional<ServiceEq> optionalServiceEq = serviceEqRepository.findById(id);

        if (optionalServiceEq.isPresent()) {
            ServiceEq existingServiceEq = optionalServiceEq.get();

            // Update fields only if they are not null
            if (serviceEq.getNom() != null) {
                existingServiceEq.setNom(serviceEq.getNom());
            }

            if (serviceEq.getChefEquipe() != null) {
                existingServiceEq.setChefEquipe(serviceEq.getChefEquipe());
            } else {
                existingServiceEq.setChefEquipe(null);
            }



            if (serviceEq.getEmployes() != null) {
                existingServiceEq.setEmployes(serviceEq.getEmployes());
            } else {
                existingServiceEq.setEmployes(new HashSet<>()); // Set to empty set if null
            }


            // Save the updated serviceEq entity
            return serviceEqRepository.save(existingServiceEq);
        } else {
            // Handle the case where the serviceEq with the given ID is not found
            throw new IllegalArgumentException("ServiceEq not found with ID: " + id);
        }
    }


    @Override
    public List<ServiceEq> getAllServiceEqs() {
        Iterable<ServiceEq> teamsIterable = serviceEqRepository.findAll();
        List<ServiceEq> teamsList = new ArrayList<>();
        for (ServiceEq serviceEq : teamsIterable) {
            teamsList.add(serviceEq);
        }
        return teamsList;
    }

    @Override
    public ServiceEq getServiceEqById(Long id) {
        return serviceEqRepository.findById(id).orElse(null);
    }

    @Override
    public ServiceEq deleteServiceEq(Long id) {
        try{
            Optional<ServiceEq> optionalServiceEq = serviceEqRepository.findById(id);



            // If the team exists, retrieve it
            ServiceEq serviceEqToDelete = optionalServiceEq.get();

            // Delete the team by its ID
            serviceEqRepository.deleteById(id);

            // Return the deleted stage
            return serviceEqToDelete;
        } catch(Exception e) {
            // If the stage does not exist, throw an exception or handle it in any other appropriate way
            throw new IllegalArgumentException("team not found");
        }

    }

    public ServiceEq getServiceEqByUserId(Long userId) {
        return serviceEqRepository.findByUserId(userId);
    }
    public ServiceEq getServiceEqByChefId(Long userId) {
        return serviceEqRepository.findByChefId(userId);
    }
}
