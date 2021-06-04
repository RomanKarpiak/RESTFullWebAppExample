package com.roman.service;

import java.util.List;


public interface ShopService<Entity> {

    void create(Entity entity);

    Entity findById(Long id);

    void update(Entity entity);

    void delete(Entity entity);

    List<Entity> findAll();
}
