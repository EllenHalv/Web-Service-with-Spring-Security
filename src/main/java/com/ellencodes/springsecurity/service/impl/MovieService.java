package com.ellencodes.springsecurity.service.impl;

import com.ellencodes.springsecurity.entity.Movie;
import com.ellencodes.springsecurity.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * service class for all crud in movie jpa repository
 */
@Service
@RequiredArgsConstructor
public class MovieService {

    @Autowired
    private final MovieRepository repository;

    public Movie save(Movie movie) {
        return repository.save(movie);
    }
    public Movie update(Movie newMovie, int id) throws Exception {
        //hämta filmen
        Optional<Movie> optionalMovie = repository.findById(id);

        if (optionalMovie.isEmpty()) {
            throw new Exception("Ingen film med id: " + id + " hittades");
        }

        //uppdatera filmen
        return repository.save(newMovie);
    }

    public Movie findById(int id) throws Exception {
        Movie movie;
        Optional<Movie> optionalMovie = repository.findById(id);

        if (optionalMovie.isEmpty()) {
            throw new Exception("Ingen film med id: " + id + " hittades");
        } else {
            movie = optionalMovie.get();
        }

        return movie;
    }

    public void deleteById(int id) throws Exception {
        //hämta filmen
        Optional<Movie> optionalMovie = repository.findById(id);

        if (optionalMovie.isPresent()) {
            //radera filmen
            repository.deleteById(id);
        } else {
            throw new Exception("Ingen film med id: " + id + " hittades");
        }
    }

    public List<Movie> findAll() {
        return repository.findAll();
    }

    public Movie findByTitle(String title) throws Exception {
        Movie movie;
        Optional<Movie> optionalMovie = repository.findByTitle(title);

        if (optionalMovie.isEmpty()) {
            throw new Exception("Ingen film med titel: " + title + " hittades");
        } else {
            movie = optionalMovie.get();
        }

        return movie;
    }
}