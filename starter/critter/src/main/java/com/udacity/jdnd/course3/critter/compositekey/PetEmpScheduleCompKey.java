package com.udacity.jdnd.course3.critter.compositekey;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PetEmpScheduleCompKey implements Serializable {

    @Column(name = "pet_id")
    long petId;

    @Column(name = "employee_id")
    long employeeId;

    @Column(name = "schedule_id")
    long scheduleId;

    public PetEmpScheduleCompKey() {
    }

    public PetEmpScheduleCompKey(long petId, long employeeId, long scheduleId) {
        this.petId = petId;
        this.employeeId = employeeId;
        this.scheduleId = scheduleId;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetEmpScheduleCompKey that = (PetEmpScheduleCompKey) o;
        return petId == that.petId && employeeId == that.employeeId && scheduleId == that.scheduleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(petId, employeeId, scheduleId);
    }
}
