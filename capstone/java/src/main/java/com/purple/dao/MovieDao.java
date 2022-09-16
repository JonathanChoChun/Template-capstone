package com.purple.dao;

import com.purple.model.Movie;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FilmDao
 */
public interface MovieDao {
    List<Movie> searchMovies(String search, int minLength, int maxLength);
    Movie getMovieById(long id);
    Movie saveMovie(Movie movie);
}