package com.spring_hibernate.learn.java.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.spring_hibernate.learn.java.dao.EmployeeDao;
import com.spring_hibernate.learn.java.dao.EmployeeDaoImpl;
import com.spring_hibernate.learn.java.domain.Employee;
import com.spring_hibernate.learn.java.domain.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rest")
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao = new EmployeeDaoImpl();

    @GetMapping(value="/allEmployees",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Employee>> getAll(){
            List<Employee> employees = employeeDao.getAllEmployee();
            return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping(value = "/insertOne",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> insertOne(@RequestBody Employee employee)
    {
        Employee newEmployee = new Employee(employee.getName());

        List<Phone> list = employee.getPhoneList();
        for(Phone p: list)
        {
            p.setOwner(newEmployee);
        }
        newEmployee.setPhoneList(list);
        employeeDao.insertOne(newEmployee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getOneEmployee/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Employee> getOneEmployee(@PathVariable int id)
    {
        return new ResponseEntity<>(employeeDao.getOneEmployee(id),HttpStatus.OK);
    }

    @PutMapping(value = "/updateOne/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> updateOne(@PathVariable int id)
    {
        employeeDao.updateById(id,"Updated Value");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteById/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteById(@PathVariable int id)
    {
        employeeDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
