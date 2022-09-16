package com.purple.handler;

import com.purple.model.Movie;
import com.purple.model.MovieSearchCriteria;
import com.purple.model.User;

import java.util.List;

public interface MovieHandlerable {
    List<Movie> getAllMoviesForUser(User user);

    List<Movie> searchMovies(User user, MovieSearchCriteria searchCriteria);

    Movie getMovieById(User user, long id, boolean includeDetail);

    Movie saveMovie(User user, Movie movie);
}
