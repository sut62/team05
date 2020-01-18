package com.cpe.backend.Employee.controller;

import com.cpe.backend.Employee.entity.Position;
import com.cpe.backend.Employee.repository.PositionRepository;

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
public class PositionController {

    @Autowired
    private final PositionRepository positionRepository;

    public PositionController(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @GetMapping("/position")
    public Collection<Position> Position() {
        return positionRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/position/{id}")
    public Optional<Position> Position(@PathVariable Long id) {
        Optional<Position> position = positionRepository.findById(id);
        return position;
    }

}