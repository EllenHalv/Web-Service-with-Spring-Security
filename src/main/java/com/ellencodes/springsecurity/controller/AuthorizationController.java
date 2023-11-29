package com.ellencodes.springsecurity.controller;

import com.ellencodes.springsecurity.entity.User;
import com.ellencodes.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users") // ändrade "/api/v1/resource" till "/users"
@RequiredArgsConstructor
public class AuthorizationController {
    @Autowired
    private UserRepository userRepository;

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
    public ResponseEntity<String> update(
            @PathVariable int id, @RequestBody User user) {
        Optional<User> userToUpdate = userRepository.findById(id);
        userRepository.save(user);
        return ResponseEntity.ok("User updated");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(
            @PathVariable int id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted");
    }
}
