package com.purple.dao;

import com.purple.model.Actor;
import java.util.List;

public interface ActorDao {
    public List<Actor> getMatchingActors(String search);
    List<Actor> getMatchingActorsByMovie(long movieId);
    Actor getActorById(long id);
    List<Actor> getAllActors();
    Actor saveActor(Actor actor);
}