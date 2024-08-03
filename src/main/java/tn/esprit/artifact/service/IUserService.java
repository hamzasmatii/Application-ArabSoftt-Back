package tn.esprit.artifact.service;

import tn.esprit.artifact.entity.ServiceEq;
import tn.esprit.artifact.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User createUser(User user);

    User updateUser(Long id, User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User deleteUser(Long id);

    public User login(String identifiant, String password);

    public User findUsersByServiceEq(Long id);

    public ServiceEq getServiceEqByUserId(Long userId);
}
