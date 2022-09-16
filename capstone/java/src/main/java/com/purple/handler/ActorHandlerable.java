package com.purple.handler;

import com.purple.model.Actor;
import com.purple.model.User;

import java.util.List;

public interface ActorHandlerable {
    Actor getActorById(User user, long id);

    List<Actor> getAllActors(User user);

    List<Actor> searchActors(User user, String searchName);
    Actor saveActor(User user, Actor actor);
}
