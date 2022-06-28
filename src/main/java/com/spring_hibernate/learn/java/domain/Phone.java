package com.spring_hibernate.learn.java.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "Phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="number")
    private long number;


    @ManyToOne(cascade = CascadeType.ALL,targetEntity = Employee.class) //Lazy fetching will ensure that when retrieving parents data, child data is not retrieved.
    @JoinColumn(name = "owner_id",referencedColumnName = "e_id")
    private Employee owner;

    public Phone() {
    }

    public Phone(int id, long number, Employee owner) {
        this.id = id;
        this.number = number;
        this.owner = owner;
    }

    public Phone(long number, Employee owner) {
        this.number = number;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    @JsonBackReference //this one is needed to prevent infinite fetching
    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}
