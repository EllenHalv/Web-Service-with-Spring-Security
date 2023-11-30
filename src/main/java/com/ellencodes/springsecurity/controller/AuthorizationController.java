package com.ellencodes.springsecurity.controller;

import com.ellencodes.springsecurity.entity.User;
import com.ellencodes.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users") // ändrade "/api/v1/resource" till "/users"
@RequiredArgsConstructor
public class AuthorizationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> create(
            @RequestBody User user
    ) {
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(user.getRole());

        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> selectOne(
            @PathVariable int id
    ) {
        Optional<User> user = userRepository.findById(id);
        return ResponseEntity.ok(user.orElse(null));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> selectAll() {
        List<User> userList = userRepository.findAll();
        return ResponseEntity.ok(userList.toString());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> update(
            @PathVariable int id,
            @RequestBody User updatedUserData) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User existingUser = optionalUser.get();

        existingUser.setFirstName(updatedUserData.getFirstName());
        existingUser.setLastName(updatedUserData.getLastName());
        existingUser.setEmail(updatedUserData.getEmail());
        existingUser.setPassword(passwordEncoder.encode(updatedUserData.getPassword()));

        User savedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(
            @PathVariable int id
    ) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted");
    }
}
