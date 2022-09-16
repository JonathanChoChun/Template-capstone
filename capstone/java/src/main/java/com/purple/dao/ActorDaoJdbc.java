package com.purple.dao;

import com.purple.model.Actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 * JDBCActorDao
 */
@Repository
public class ActorDaoJdbc implements ActorDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ActorDaoJdbc(DataSource datasource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    @Override
    public List<Actor> getMatchingActors(String search) {
        List<Actor> matchingActors = new ArrayList<>();
        String sql = """
                select
                        p.*
                    from
                    person p
                    join movie_actor ma
                        on p.person_id = ma.actor_id
                where
                    p.person_name ilike :name
                order by p.person_name""";
        Map<String,Object> params = new HashMap<>();
        params.put("name","%" + search + "%");
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, params);
        return mapRowsToActors(results,false);
    }
    @Override
    public List<Actor> getMatchingActorsByMovie(long movieId) {
        List<Actor> matchingActors = new ArrayList<>();
        String sql = """
                select
                        p.*
                    from
                    person p
                    join movie_actor ma
                        on p.person_id = ma.actor_id
                where
                    ma.movie_id = :id
                order by p.person_name""";
        Map<String,Object> params = new HashMap<>();
        params.put("id",movieId);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, params);
        return mapRowsToActors(results,false);
    }
    @Override
    public Actor getActorById(long id) {
        String sql = """
                select
                        p.*
                    from
                    person p
                    join movie_actor ma
                        on p.person_id = ma.actor_id
                where
                    p.person_id = :id
                order by p.person_name""";
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, params);
        return mapRowToActor(results,true);
    }
    @Override
    public List<Actor> getAllActors() {
        List<Actor> matchingActors = new ArrayList<>();
        String sql = """
                select
                        p.*
                    from
                    person p
                    join movie_actor ma
                        on p.person_id = ma.actor_id
                order by p.person_name""";
        Map<String,Object> params = new HashMap<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, params);
        return mapRowsToActors(results,false);
    }
    @Override
    public Actor saveActor(Actor actor) {
        String sql = """
                update person
                       set                       
                       person_name = :name,
                       birthday = :birthdate,
                       deathday = :deathdate,
                       biography = :biography,
                       profile_path = :profileUri,
                       home_page = :homeUri
                where
                    person_id = :id; """;
        Map<String,Object> params = new HashMap<>();
        params.put("id",actor.getId());
        params.put("name",actor.getName());
        params.put("birthdate",actor.getBirthDay());
        params.put("deathdate",actor.getDeathDay());
        params.put("biography",actor.getBiography());
        params.put("profileUri",actor.getProfilePath());
        params.put("homeUri",actor.getHomePage());
        jdbcTemplate.update(sql,params);
        return getActorById(actor.getId());
    }

    private List<Actor> mapRowsToActors(SqlRowSet results, boolean showDetail){
        List<Actor> actors = new ArrayList<>();
        while (results.next()){
            actors.add(mapRowToActor(results,showDetail));
        }
        return actors;
    }

    private Actor mapRowToActor(SqlRowSet results, boolean showDetail) {
        Actor actor = new Actor();
        actor.setId(results.getLong("person_id"));
        actor.setName(results.getString("person_name"));
        if (showDetail) {
            actor.setBiography(results.getString("biography"));
//        actor.setBirthDay(results.getDate("birthdate"));
//        actor.setDeathDay(results.getDate("deathdate"));
            actor.setHomePage(results.getString("home_page"));
            actor.setProfilePath(results.getString("profile_path"));
        }
        return actor;
    }

}