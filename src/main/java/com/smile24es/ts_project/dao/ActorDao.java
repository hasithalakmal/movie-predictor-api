package com.smile24es.ts_project.dao;


import com.smile24es.ts_project.beans.Actor;
import java.util.List;

/**
 * Created by hasithagamage on 5/15/17.
 */
public interface ActorDao {

    List<Actor> findAllActors();

    void saveActor(Actor actor);
    
    Actor findActorByName(String actorName);

    void deleteAllActors();
}
