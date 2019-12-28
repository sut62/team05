package com.cpe.backend.Employee.controller;

import com.cpe.backend.Employee.entity.Phonetype;
import com.cpe.backend.Employee.repository.PhonetypeRepository;


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
public class PhonetypeController {

    @Autowired
    private final PhonetypeRepository phonetypeRepository;

    public PhonetypeController(PhonetypeRepository phonetypeRepository) {
        this.phonetypeRepository = phonetypeRepository;
    }

    @GetMapping("/phonetype")
    public Collection<Phonetype> Phonetype() {
        return phonetypeRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/phonetype/{id}")
    public Optional<Phonetype> Phonetype(@PathVariable Long id) {
        Optional<Phonetype> phonetype = phonetypeRepository.findById(id);
        return phonetype;
    }

}