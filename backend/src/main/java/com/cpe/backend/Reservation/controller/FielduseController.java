package com.cpe.backend.Reservation.controller;

import com.cpe.backend.Reservation.entity.Fielduse;
import com.cpe.backend.Reservation.repository.FielduseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class FielduseController {

    @Autowired
    private final FielduseRepository fielduseRepository;

    public FielduseController(FielduseRepository fielduseRepository) {
        this.fielduseRepository = fielduseRepository;
    }

    @GetMapping("/fielduse")
    public Collection<Fielduse> Fielduses() {
        return fielduseRepository.findAll().stream().collect(Collectors.toList());
    }

}