package com.ellencodes.springsecurity.controller;

import com.ellencodes.springsecurity.entity.Movie;
import com.ellencodes.springsecurity.service.impl.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * controller class. skickar vidare request till service class som använder jpa repository
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @PostMapping
    public ResponseEntity<Movie> addMovie(
            @RequestBody Movie movie
    ) {
        try {
            return ResponseEntity.ok(service.save(movie));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(
            @RequestBody Movie movie,
            @PathVariable int id
    ) {
        try {
            return ResponseEntity.ok(service.update(movie, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(
            @PathVariable int id
    ) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok("Film raderades: " + id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Movie>> getOneMovie(
            @PathVariable int id
    ) {
        try {
            return ResponseEntity.ok(Optional.ofNullable(service.findById(id)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {

        List<Movie> movielist = service.findAll();

        return ResponseEntity.ok(movielist);
    }

    @GetMapping("/get") // todo titeln behöver vara i en payload annars kan man ej göra mellanslag
    public ResponseEntity<Optional<Movie>> getOneByTitle(
            @RequestBody String title
    ) {
        try {
            return ResponseEntity.ok(Optional.ofNullable(service.findByTitle(title)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

