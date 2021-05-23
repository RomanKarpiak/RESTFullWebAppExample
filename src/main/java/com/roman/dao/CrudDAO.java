package com.roman.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrudDAO<Entity, Key> {

    void create(Entity entity);

    Entity findById(Key key);

    void update(Entity entity);

    void delete(Entity entity);

    List<Entity> findAll();

}
