package com.roman.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ShopService<Entity> {

    void create(Entity entity);

    Entity findById(Long id);

    void update(Entity entity);

    void delete(Entity entity);

    List<Entity> findAll();
}
