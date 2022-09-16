package com.purple.handler;

import com.purple.dao.ActorDao;
import com.purple.model.Actor;
import com.purple.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorHandler implements ActorHandlerable {
    private final ActorDao actorDao;

    @Autowired
    public ActorHandler(ActorDao actorDao) {
        this.actorDao = actorDao;
    }
    @Override
    public Actor getActorById(User user, long id) {
        return actorDao.getActorById(id);
    }
    @Override
    public List<Actor> getAllActors(User user){
        return actorDao.getAllActors();
    }
    @Override
    public List<Actor> searchActors(User user, String searchName){
        return actorDao.getMatchingActors(searchName);
    }

    @Override
    public Actor saveActor(User user, Actor actor) {
        return actorDao.saveActor(actor);
    }
}
