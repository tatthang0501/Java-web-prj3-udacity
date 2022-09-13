package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.critterenum.EmployeeSkill;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity
public class Employee extends User {

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @JoinTable(
            name = "EMPLOYEE_SKILL",
            joinColumns=@JoinColumn(name="employee_id")
    )
    private Set<EmployeeSkill> skills;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @JoinTable(
            name = "DAYS_AVAILABLE",
            joinColumns=@JoinColumn(name="employee_id")
    )
    private Set<DayOfWeek> daysAvailable;

    @ManyToMany(mappedBy = "listEmployee")
    private List<Schedule> listSchedule;

    public Employee() {
        super();
    }

    public Employee(long id, String name, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable, List<Schedule> listSchedule) {
        super(id, name);
        this.skills = skills;
        this.daysAvailable = daysAvailable;
        this.listSchedule = listSchedule;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public List<Schedule> getListSchedule() {
        return listSchedule;
    }

    public void setListSchedule(List<Schedule> listSchedule) {
        this.listSchedule = listSchedule;
    }

}
