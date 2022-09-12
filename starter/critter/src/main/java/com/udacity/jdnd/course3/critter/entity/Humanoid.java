package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Humanoid {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="name")
    private String name;

    public Humanoid() {
    }

    public Humanoid(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
