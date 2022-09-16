package com.purple.controller;

import com.purple.dao.MovieDao;
import com.purple.handler.MovieHandlerable;
import com.purple.model.Movie;
import com.purple.model.MovieSearchCriteria;
import com.purple.model.User;
import com.purple.security.Authorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FilmSearchController
 */
@RestController
@RequestMapping("/api")
public class MovieController extends BaseController {

    private final MovieHandlerable movieHandler;

    @Autowired
    public MovieController(MovieHandlerable movieHandler) {
        this.movieHandler = movieHandler;
    }

    @GetMapping(value = "/movieSearch/{search}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Authorized
    public ResponseEntity<?> searchMovie(HttpServletRequest request, HttpServletResponse response,@PathVariable String search) {
        if (search == null) {
            return ResponseEntity.badRequest().body("Search is empty");
        }
        User user = super.getCurrentUserFromAttribute(request);
        MovieSearchCriteria searchCriteria = new MovieSearchCriteria();
        searchCriteria.setTitle(search);
        searchCriteria.setMinLength(0);
        searchCriteria.setMaxLength(10000);
        return ResponseEntity.ok(this.movieHandler.searchMovies(user,searchCriteria));
    }
    @PostMapping(value = "/movie/search",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Authorized
    public ResponseEntity<?> searchMovie(HttpServletRequest request, HttpServletResponse response, @RequestBody MovieSearchCriteria searchCriteria) {
        if (searchCriteria == null) {
            return ResponseEntity.badRequest().body("Search is empty");
        }
        User user = super.getCurrentUserFromAttribute(request);

        return ResponseEntity.ok(this.movieHandler.searchMovies(user,searchCriteria));
    }
    @GetMapping(value = "/movie/all",
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<?> getAllActors(HttpServletRequest request, HttpServletResponse response) {
        User user = super.getCurrentUserFromAttribute(request);
        return ResponseEntity.ok(this.movieHandler.getAllMoviesForUser(user));
    }

    @GetMapping(value = "/movie/detail/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Authorized
    public ResponseEntity<?> getMovieById(HttpServletRequest request, HttpServletResponse response, @PathVariable long id) {
        User user = super.getCurrentUserFromAttribute(request);
        return ResponseEntity.ok(this.movieHandler.getMovieById(user,id,true));
    }
    @PutMapping(value = "/movie/detail/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Authorized(requiredRole = "Admin")
    public ResponseEntity<?> updateMovieById(HttpServletRequest request, HttpServletResponse response, @RequestBody Movie movie) {
        User user = super.getCurrentUserFromAttribute(request);
        return ResponseEntity.ok(this.movieHandler.saveMovie(user,movie));
    }
}