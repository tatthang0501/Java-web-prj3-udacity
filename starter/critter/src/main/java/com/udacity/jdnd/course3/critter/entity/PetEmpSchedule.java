package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.compositekey.PetEmpScheduleCompKey;

import javax.persistence.*;

@Entity
@Table(name = "PET_EMP_SCHEDULE")
public class PetEmpSchedule {

    @EmbeddedId
    private PetEmpScheduleCompKey petEmpScheduleCompKey;

    @ManyToOne
    @MapsId("petId")
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @MapsId("scheduleId")
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}
