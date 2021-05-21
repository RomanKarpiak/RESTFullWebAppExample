package com.roman.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.roman.views.Views;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class StoreItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Private.class)
    private Long id;

    @Column(name = "name")
    @JsonView(Views.Private.class)
    private String name;

    public StoreItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
