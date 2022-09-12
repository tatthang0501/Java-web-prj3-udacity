package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer extends Humanoid {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="notes")
    private String notes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private List<Pet> petList;

    public Customer() {
        super();
    }

    public Customer(long id, String name, long id1, String phoneNumber, String notes, List<Pet> petList) {
        super(id, name);
        this.id = id1;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.petList = petList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }
}
