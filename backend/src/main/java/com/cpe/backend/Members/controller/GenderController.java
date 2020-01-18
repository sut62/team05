package com.cpe.backend.Members.controller;

import com.cpe.backend.Members.entity.Gender;
import com.cpe.backend.Members.repository.GenderRepository;

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
public class GenderController {

    @Autowired
    private final GenderRepository genderRepository;

    public GenderController(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @GetMapping("/gender")
    public Collection<Gender> Gender() {
        return genderRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/gender/{id}")
    public Optional<Gender> Gender(@PathVariable Long id) {
        Optional<Gender> gender = genderRepository.findById(id);
        return gender;
    }

}