
package com.backendSOA.backendSOA.controllers;

import com.backendSOA.backendSOA.configurations.JwtToken;
import com.backendSOA.backendSOA.models.User;
import com.backendSOA.backendSOA.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok((List<User>) userRepository.findAll());
        } catch (Error error) {
            return ResponseEntity.status(500).body("error getting all users");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok((User) userRepository.findById(id).orElse(null));
        } catch (Error error) {
            return ResponseEntity.status(500).body("error getting user");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@Valid @RequestBody User userSignUpInfo) {
        try {
            User user = new User();
            user.setEmail(userSignUpInfo.getEmail());
            user.setPassword(userSignUpInfo.getPassword());
            user.setRole(userSignUpInfo.getRole());
            user.setFirstName(userSignUpInfo.getFirstName());
            user.setLastName(userSignUpInfo.getLastName());
            user.setBirthDate(userSignUpInfo.getBirthDate());
            user = userRepository.save(user);
            HashMap<String, Object> response = new HashMap<>();
            JwtToken jwt = new JwtToken();
            String token = jwt.generateToken(user);
            response.put("user", user);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (Error error) {
            return ResponseEntity.status(500).body("error signing up");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @PathVariable Long id, @RequestBody User userInfo) {
        try {
            User user = userRepository.findById(id).orElse(null);
            if (user != null) {
                userInfo.setId(id);
                userRepository.save(userInfo);
                return ResponseEntity.ok(userInfo);
            } else {
                throw new Error("user does not exist");
            }
        } catch (Error error) {
            return ResponseEntity.status(500).body(error.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody User userLoginInfo) {
        try {
            User user = userRepository.findByEmail(userLoginInfo.getEmail());
            if (user != null) {
                if (user.getPassword().equals(userLoginInfo.getPassword())) {
                    JwtToken jwt = new JwtToken();
                    String token = jwt.generateToken(user);
                    HashMap<String, Object> response = new HashMap<>();
                    response.put("user", user);
                    response.put("token", token);
                    return ResponseEntity.ok(response);
                } else {
                    throw new Error("wrong password");
                }
            } else {
                throw new Error("user does not exist");
            }
        } catch (Error error) {
            return ResponseEntity.status(500).body(error.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            User user = userRepository.findById(id).orElse(null);
            if (user != null) {
                userRepository.delete(user);
                return ResponseEntity.ok("user deleted");
            } else {
                throw new Error("user does not exist");
            }
        } catch (Error error) {
            return ResponseEntity.status(500).body(error.getMessage());
        }
    }
}