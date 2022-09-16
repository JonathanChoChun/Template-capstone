package com.purple.dao;

import com.purple.model.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

/**
 * JDBCFilmDao implements FilmDao
 */
@Component
public class MovieDaoJdbc implements MovieDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public MovieDaoJdbc(DataSource datasource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<Movie> searchMovies(String search, int minLength, int maxLength) {
        List<Movie> matchingFilms = new ArrayList<>();

        String sql = """
                select
                    m.*,
                    p.person_name as director_name
                from
                    movie m
                    left join person p
                        on p.person_id = m.director_id
                where
                        (
                          m.title ilike :search
                          or
                          m.overview ilike :search
                        )
                        and
                        (
                          m.length_minutes between :minLength and :maxLength
                        );
                """;
        Map<String,Object> params = new HashMap<>();
        params.put("search","%" + search + "%");
        params.put("minLength",minLength);
        params.put("maxLength",maxLength);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, params);
        while (results.next()) {
            matchingFilms.add(mapRowToFilm(results));
        }
        return matchingFilms;
    }
    public Movie getMovieById(long id) {
        String sql = """
                select
                    m.*,
                    p.person_name as director_name
                from
                    movie m
                    left join person p
                        on p.person_id = m.director_id
                where
                        m.movie_id = :id;
                """;
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, params);
        if (results.next()) {
            return mapRowToFilm(results);
        }
        return null;
    }

    public Movie saveMovie(Movie movie){
        String sql = """
                update movie
                    set
                        overview = :overview
                    where
                        movie_id = :id;""";
        Map<String,Object> params = new HashMap<>();
        params.put("overview",movie.getOverview());
        params.put("id",movie.getId());
        int numRecords = jdbcTemplate.update(sql,params);
        //should have only done one
        //if not there was an issue...
        //also, may need to get the updated on in case there were
        //columns updated (like modified date, etc.)
        return movie;
    }
    private Movie mapRowToFilm(SqlRowSet results) {
        Movie movie = new Movie();
        movie.setId(results.getLong("movie_id"));
        movie.setCollectionId(results.getLong("collection_id"));
        movie.setDirectorId(results.getLong("director_id"));
        movie.setTitle(results.getString("title"));
        movie.setDirectorName(results.getString("director_name"));
        movie.setHomePage(results.getString("home_page"));
        movie.setPosterPath(results.getString("poster_path"));
        movie.setOverview(results.getString("overview"));
        movie.setTagline(results.getString("tagline"));
        movie.setLengthMinutes(results.getInt("length_minutes"));
        movie.setReleaseDate(results.getDate("release_date"));

        return movie;
    }

}