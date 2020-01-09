package com.cpe.backend.Employee.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;


import com.cpe.backend.Employee.entity.Employee;
import com.cpe.backend.Employee.entity.Phonetype;
import com.cpe.backend.Members.entity.Province;
import com.cpe.backend.Employee.entity.Position;

import com.cpe.backend.Employee.repository.EmployeeRepository;
import com.cpe.backend.Employee.repository.PhonetypeRepository;
import com.cpe.backend.Members.repository.ProvinceRepository;
import com.cpe.backend.Employee.repository.PositionRepository;


import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class EmployeeController {
    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private PhonetypeRepository phonetypeRepository;

    EmployeeController(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/Employee")
    public Collection<Employee> Employee() {
        return employeeRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/Employee/{name}/{email}/{password}/{phonenumber}/{position_id}/{phonetype_id}/{province_id}")
    public Employee newEmployee(final Employee newEmployee, @PathVariable final String name,
            @PathVariable final String email,
            @PathVariable final String password, 
            @PathVariable final String phonenumber,
            @PathVariable final long position_id, 
            @PathVariable final long phonetype_id,
            @PathVariable final long province_id)

    {
        final Position position = positionRepository.findById(position_id);
        final Phonetype phonetype = phonetypeRepository.findById(phonetype_id);
        final Province province = provinceRepository.findById(province_id);


    newEmployee.setName(name);
    newEmployee.setTimeRegis(new Date());
    newEmployee.setEmail(email);
    newEmployee.setPassword(password);
    newEmployee.setPhonenumber(phonenumber);
    newEmployee.setPosition(position);
    newEmployee.setPhonetype(phonetype);
    newEmployee.setProvince(province);
    return employeeRepository.save(newEmployee); 
    
    }
}