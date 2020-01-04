package com.cpe.backend.Sportequipment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Collection;

import java.util.stream.Collectors;

import com.cpe.backend.Employee.entity.Employee;
import com.cpe.backend.Employee.repository.EmployeeRepository;
import com.cpe.backend.Sportequipment.entity.Category;
import com.cpe.backend.Sportequipment.entity.Sporttype;
import com.cpe.backend.Sportequipment.entity.Sportequipment;

import com.cpe.backend.Sportequipment.repository.CategoryRepository;
import com.cpe.backend.Sportequipment.repository.SporttypeRepository;
import com.cpe.backend.Sportequipment.repository.SportequipmentRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class SportequipmentController {
    @Autowired
    private final SportequipmentRepository sportequipmentRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SporttypeRepository sporttypeRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    SportequipmentController(SportequipmentRepository sportequipmentRepository) {
        this.sportequipmentRepository = sportequipmentRepository;
    }

    @GetMapping("/sportequipment")
    public Collection<Sportequipment> Sportequipment() {
        return sportequipmentRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/Sportequipment/{emp_id}/{category_id}/{se_name}/{type_id}/{brand}/{price}/{date}")
    public Sportequipment newSportequipment(Sportequipment newSportequipment,

            @PathVariable long emp_id, 
            @PathVariable long category_id, 
            @PathVariable String se_name,
            @PathVariable long type_id, 
            @PathVariable String brand, 
            @PathVariable long price,
            @PathVariable Date date) {

         Employee employee = employeeRepository.findById(emp_id);
         Sporttype sporttype = sporttypeRepository.findById(type_id);
         Category category = categoryRepository.findById(category_id);

    newSportequipment.setBrand(brand);
    newSportequipment.setName(se_name);
    newSportequipment.setPrice(price);
    newSportequipment.setDate(date);
    newSportequipment.setEmployee(employee);
    newSportequipment.setSporttype(sporttype);
    newSportequipment.setCategory(category);
    

    return sportequipmentRepository.save(newSportequipment);
    
     }
}