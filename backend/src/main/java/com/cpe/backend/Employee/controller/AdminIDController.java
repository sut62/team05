package com.cpe.backend.Employee.controller;

import com.cpe.backend.Employee.entity.AdminID;
import com.cpe.backend.Employee.repository.AdminIDRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AdminIDController {

    @Autowired
    private final AdminIDRepository adminIDRepository;

    public AdminIDController(AdminIDRepository adminIDRepository) {
        this.adminIDRepository = adminIDRepository;
    }

    @GetMapping("/adminID")
    public Collection<AdminID> AdminID() {
        return adminIDRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/adminID/{id}")
    public Optional<AdminID> AdminID(@PathVariable Long id) {
        Optional<AdminID> adminID = adminIDRepository.findById(id);
        return adminID;
    }
    @GetMapping("/check/{email}/{pass}")
    public Collection<AdminID> getCheck(@PathVariable("email") String email,
                                      @PathVariable("pass") String pass) {
        return adminIDRepository.findCheck(email,pass);
    }

}