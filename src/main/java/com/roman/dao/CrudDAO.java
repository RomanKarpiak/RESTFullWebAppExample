package com.roman.dao;

import java.util.List;


public interface CrudDAO<Entity> {

    void create(Entity entity);

    Entity findById(Long id);

    void update(Entity entity);

    void delete(Entity entity);

    List<Entity> findAll();

}
