package com.ellencodes.springsecurity.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.ellencodes.springsecurity.entity.Movie;
import com.ellencodes.springsecurity.service.impl.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {MovieController.class})
@ExtendWith(SpringExtension.class)
class MovieControllerDiffblueTest {
    @Autowired
    private MovieController movieController;

    @MockBean
    private MovieService movieService;

    /**
     * Method under test:  {@link MovieController#addMovie(Movie)}
     */
    @Test
    void testAddMovie() throws Exception {
        when(movieService.findAll()).thenReturn(new ArrayList<>());

        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Dr");
        movie.setYear(1);
        String content = (new ObjectMapper()).writeValueAsString(movie);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link MovieController#deleteMovie(int)}
     */
    @Test
    void testDeleteMovie() throws Exception {
        doNothing().when(movieService).deleteById(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/movies/{id}", 1);
        MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Film raderades: 1"));
    }

    /**
     * Method under test: {@link MovieController#deleteMovie(int)}
     */
    @Test
    void testDeleteMovie2() throws Exception {
        doThrow(new Exception("foo")).when(movieService).deleteById(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/movies/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test:  {@link MovieController#getMovies()}
     */
    @Test
    void testGetMovies() throws Exception {
        when(movieService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/movies");
        MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link MovieController#getOneByTitle(String)}
     */
    @Test
    void testGetOneByTitle() throws Exception {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Dr");
        movie.setYear(1);
        when(movieService.findByTitle(Mockito.<String>any())).thenReturn(movie);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/movies/get")
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString("foo"));
        MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"title\":\"Dr\",\"year\":1}"));
    }

    /**
     * Method under test:  {@link MovieController#getOneByTitle(String)}
     */
    @Test
    void testGetOneByTitle2() throws Exception {
        when(movieService.findByTitle(Mockito.<String>any())).thenThrow(new Exception("foo"));
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/movies/get")
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString("foo"));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link MovieController#getOneMovie(int)}
     */
    @Test
    void testGetOneMovie() throws Exception {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Dr");
        movie.setYear(1);
        when(movieService.findById(anyInt())).thenReturn(movie);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/movies/{id}", 1);
        MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"title\":\"Dr\",\"year\":1}"));
    }

    /**
     * Method under test:  {@link MovieController#getOneMovie(int)}
     */
    @Test
    void testGetOneMovie2() throws Exception {
        when(movieService.findById(anyInt())).thenThrow(new Exception("foo"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/movies/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link MovieController#updateMovie(Movie, int)}
     */
    @Test
    void testUpdateMovie() throws Exception {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Dr");
        movie.setYear(1);
        when(movieService.update(Mockito.<Movie>any(), anyInt())).thenReturn(movie);

        Movie movie2 = new Movie();
        movie2.setId(1L);
        movie2.setTitle("Dr");
        movie2.setYear(1);
        String content = (new ObjectMapper()).writeValueAsString(movie2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/movies/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"title\":\"Dr\",\"year\":1}"));
    }

    /**
     * Method under test:  {@link MovieController#updateMovie(Movie, int)}
     */
    @Test
    void testUpdateMovie2() throws Exception {
        when(movieService.update(Mockito.<Movie>any(), anyInt())).thenThrow(new Exception("foo"));

        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Dr");
        movie.setYear(1);
        String content = (new ObjectMapper()).writeValueAsString(movie);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/movies/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}
