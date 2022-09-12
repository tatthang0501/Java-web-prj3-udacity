package com.udacity.jdnd.course3.critter.pet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name="pet")
public class Pet {

    @Id
    @GeneratedValue
    private long id;
    @Column(name="pet_type")
    private PetType petType;
    @Column()
    private String name;
    @Column(name="owner_id")
    private long ownerId;
    @Column(name="birth_date")
    private LocalDate birthDate;
    @Column()
    private String notes;
    public Pet() {
    }

    public Pet(long id, PetType petType, String name, long ownerId, LocalDate birthDate, String notes) {
        this.id = id;
        this.petType = petType;
        this.name = name;
        this.ownerId = ownerId;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
