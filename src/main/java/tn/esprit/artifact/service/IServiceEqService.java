package tn.esprit.artifact.service;

import tn.esprit.artifact.entity.ServiceEq;

import java.util.List;

public interface IServiceEqService {
    ServiceEq createServiceEq(ServiceEq serviceEq);

    ServiceEq updateServiceEq(Long id, ServiceEq serviceEq);

    List<ServiceEq> getAllServiceEqs();

    ServiceEq getServiceEqById(Long id);

    ServiceEq deleteServiceEq(Long id);
    public ServiceEq getServiceEqByUserId(Long userId);

    public ServiceEq getServiceEqByChefId(Long userId);
}
