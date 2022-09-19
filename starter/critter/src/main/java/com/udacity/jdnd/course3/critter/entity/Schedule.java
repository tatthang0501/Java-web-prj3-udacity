package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.critterenum.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name="date")
    private LocalDate date;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @JoinTable(
            name = "USED_ACTIVITIES",
            joinColumns = @JoinColumn(name="schedule_id")
    )
    private Set<EmployeeSkill> activities;

    @ManyToMany
    @JoinTable(name = "EMPLOYEE_SCHEDULE",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> listEmployee;

    @ManyToMany
    @JoinTable(name = "PET_SCHEDULE",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id"))
    private List<Pet> listPet;

    public Schedule() {
    }

    public Schedule(long id, LocalDate date, Set<EmployeeSkill> activities, List<Employee> listEmployee, List<Pet> listPet) {
        this.id = id;
        this.date = date;
        this.activities = activities;
        this.listEmployee = listEmployee;
        this.listPet = listPet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }

    public List<Employee> getListEmployee() {
        return listEmployee;
    }

    public void setListEmployee(List<Employee> listEmployee) {
        this.listEmployee = listEmployee;
    }

    public List<Pet> getListPet() {
        return listPet;
    }

    public void setListPet(List<Pet> listPet) {
        this.listPet = listPet;
    }
}
