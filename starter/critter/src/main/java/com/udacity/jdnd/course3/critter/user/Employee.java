package com.udacity.jdnd.course3.critter.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
public class Employee extends Humanoid{

    @Column(name="skills")
    private String skills;

    @Column
    private Set<DayOfWeek> daysAvailable;

}
