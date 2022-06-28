package com.spring_hibernate.learn.java.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Employee")  //If the name is similar to database table name, then name can be avoided.
public class Employee {

    @Id
    @Column(name = "e_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //this one generates id in this application and send it to the database.
    private int id;

    @Column(name = "name") //this is also can be avoided if the name is similar to database.
    private String name;

    //Mapped by needs in oneToMany relation. This will point to its object in the child class. Cascade will make sure that all operations performed in this class will also affect in child class.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner",fetch = FetchType.EAGER,orphanRemoval = true) //Eager fetching will also fetch its children.
    private List<Phone> phoneList;

    public Employee() {
    }

    public Employee( String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Employee(String name, List<Phone> phoneList) {
        this.name = name;
        this.phoneList = phoneList;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonManagedReference //this one is needed to prevent infinite fetching
    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }


    @Override
    public String toString() {
      StringBuilder display = new StringBuilder("Employee{" + "id=" + id + ", name='" + name + '\'');
      for (Phone p : phoneList)
      {
         display.append(p.toString());
      }

      return display.toString();
    }
}
