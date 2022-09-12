package com.udacity.jdnd.course3.critter.user;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Humanoid {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;
}
