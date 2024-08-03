package tn.esprit.artifact.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.artifact.entity.ServiceEq;
import tn.esprit.artifact.entity.User;
import tn.esprit.artifact.service.IUserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User addedUser = userService.createUser(user);
        return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> showUserByid(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @RequestBody User updatedUser) {
        try {
            User updated = userService.updateUser(userId, updatedUser);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        try {
            User deletedUser = userService.deleteUser(userId);

            // Create the response message
            String message = "User with ID " + userId + " deleted successfully.";

            // Create the response headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Message", message);
            // Return the response entity with the response object and status OK
            return new ResponseEntity<>(headers, HttpStatus.OK);
        }

        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/user/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginRequest, HttpSession session) {
        String identifiantUser = loginRequest.get("identifiantUser");
        String password = loginRequest.get("mdp");

        User user = userService.login(identifiantUser, password);
        Map<String, Object> response = new HashMap<>();

        if (user != null) {
            session.setAttribute("user", user);
            response.put("message", "Login successful");
            response.put("role", user.getType());
        } else {
            response.put("message", "Invalid credentials");
        }

        return response;
    }

    @GetMapping("/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logout successful";
    }

    @GetMapping("/user/service/{userId}")
    public ResponseEntity<ServiceEq> getServiceEqByUserId(@PathVariable Long userId) {
        ServiceEq serviceEq = userService.getServiceEqByUserId(userId);
        return ResponseEntity.ok(serviceEq);
    }


}
