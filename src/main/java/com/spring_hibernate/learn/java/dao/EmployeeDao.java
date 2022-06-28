package com.spring_hibernate.learn.java.dao;

import com.spring_hibernate.learn.java.domain.Employee;

import java.util.List;

public interface EmployeeDao {
    public List<Employee> getAllEmployee();
    public void insertOne(Employee e);
    public Employee getOneEmployee(int id);
    public void deleteById(int id);
    public void updateById(int id, String name);
}
