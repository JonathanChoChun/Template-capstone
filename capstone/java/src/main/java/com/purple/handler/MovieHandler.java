package com.purple.handler;

import com.purple.dao.ActorDao;
import com.purple.dao.MovieDao;
import com.purple.model.Movie;
import com.purple.model.MovieSearchCriteria;
import com.purple.model.User;
import com.purple.service.ExternalMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieHandler implements MovieHandlerable {
    private final MovieDao movieDao;
    private final ActorDao actorDao;
    private final ExternalMovieService movieService;
    @Autowired
    public MovieHandler(MovieDao movieDao, ActorDao actorDao,ExternalMovieService movieService) {
        this.movieDao = movieDao;
        this.actorDao = actorDao;
        this.movieService = movieService;
    }

    @Override
    public List<Movie> getAllMoviesForUser(User user){
        return movieDao.searchMovies("%",0,10000);
    }
    @Override
    public List<Movie> searchMovies(User user, MovieSearchCriteria searchCriteria){
        return movieDao.searchMovies(searchCriteria.getTitle(),0,10000);
    }
    @Override
    public Movie getMovieById(User user, long id, boolean includeDetail){
        Movie movie = movieDao.getMovieById(id);
        if (includeDetail) {
            //get from database call to get actors
            movie.setActors(actorDao.getMatchingActorsByMovie(id));
            //call external API to get other data
            movieService.getMovieFromExternal(movie);
        }

        return movie;
    }
    @Override
    public Movie saveMovie(User user, Movie movie){
        return this.movieDao.saveMovie(movie);
    }
}
