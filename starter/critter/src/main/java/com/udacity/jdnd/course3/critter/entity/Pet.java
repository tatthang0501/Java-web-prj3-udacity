package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.critterenum.PetType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(name="pet_type")
    @Enumerated(EnumType.STRING)
    private PetType petType;

    @Column()
    private String name;

    @Column(name="birth_date")
    private LocalDate birthDate;

    @Column()
    private String notes;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer owner;

    @ManyToMany(mappedBy = "listPet")
    private List<Schedule> listSchedule;

    public Pet() {
    }

    public Pet(long id, PetType petType, String name, LocalDate birthDate, String notes, Customer owner, List<Schedule> listSchedule) {
        this.id = id;
        this.petType = petType;
        this.name = name;
        this.birthDate = birthDate;
        this.notes = notes;
        this.owner = owner;
        this.listSchedule = listSchedule;
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

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public List<Schedule> getListSchedule() {
        return listSchedule;
    }

    public void setListSchedule(List<Schedule> listSchedule) {
        this.listSchedule = listSchedule;
    }
}
