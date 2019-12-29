package com.cpe.backend.Reservation.controller;

import com.cpe.backend.Reservation.entity.Fieldtype;
import com.cpe.backend.Reservation.repository.FieldtypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class FieldtypeController {

    @Autowired
    private final FieldtypeRepository fieldtypeRepository;

    public FieldtypeController(FieldtypeRepository fieldtypeRepository) {
        this.fieldtypeRepository = fieldtypeRepository;
    }

    @GetMapping("/fieldtype")
    public Collection<Fieldtype> Fieldtypes() {
        return fieldtypeRepository.findAll().stream().collect(Collectors.toList());
    }

}