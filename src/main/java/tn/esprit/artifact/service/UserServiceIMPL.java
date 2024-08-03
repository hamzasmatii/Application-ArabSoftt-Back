package tn.esprit.artifact.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.artifact.entity.ServiceEq;
import tn.esprit.artifact.entity.User;
import tn.esprit.artifact.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceIMPL implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            // Update fields only if they are not null

            if (user.getMdp() != null) {
                existingUser.setMdp(user.getMdp());
            }
            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getNumber() != null) {
                existingUser.setNumber(user.getNumber());
            }
            if (user.getNom() != null) {
                existingUser.setNom(user.getNom());
            }
            if (user.getPrenom() != null) {
                existingUser.setPrenom(user.getPrenom());
            }
            if (user.getType() != null) {
                existingUser.setType(user.getType());
            }

            if (user.getPoste() != null) {
                existingUser.setPoste(user.getPoste());
            }else{
                existingUser.setPoste(null);
            }

            if (user.getServiceEq() != null) {
                existingUser.setServiceEq(user.getServiceEq());
            }else{
                existingUser.setServiceEq(null);

            }

           /* if (user.getChefEquipeService() != null) {
                existingUser.setChefEquipeService(user.getChefEquipeService());
            }else{
                existingUser.setServiceEq(null);

            }*/


            // Save the updated user entity
            return userRepository.save(existingUser);
        } else {
            // Handle the case where the user with the given ID is not found
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
    }


    @Override
    public List<User> getAllUsers() {
        Iterable<User> usersIterable = userRepository.findAll();
        List<User> usersList = new ArrayList<>();
        for (User user : usersIterable) {
            usersList.add(user);
        }
        return usersList;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User deleteUser(Long id) {
        try{
            Optional<User> optionalUser = userRepository.findById(id);



            // If the user exists, retrieve it
            User userToDelete = optionalUser.get();

            // Delete the user by its ID
            userRepository.deleteById(id);

            // Return the deleted stage
            return userToDelete;
        } catch(Exception e) {
            // If the stage does not exist, throw an exception or handle it in any other appropriate way
            throw new IllegalArgumentException("user not found");
        }

    }

    @Override
    public User login(String identifiant, String password) {
        User user = userRepository.findUsersByIdentifiantUser(identifiant);
        if (user != null && user.getMdp().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public User findUsersByServiceEq(Long id) {
        User user = userRepository.findUsersByServiceEqId(id);
        if (user != null ) {
            return user;
        }
        return null;
    }

    public ServiceEq getServiceEqByUserId(Long userId) {
        // Recherchez l'utilisateur par son ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Renvoyer l'objet ServiceEq associé
        return user.getServiceEq();
    }

}
