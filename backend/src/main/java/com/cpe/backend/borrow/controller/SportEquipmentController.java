package com.cpe.backend.borrow.controller;

import com.cpe.backend.borrow.entity.SportEquipment;
import com.cpe.backend.borrow.repository.SportEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class SportEquipmentController {
    @Autowired
    private final SportEquipmentRepository sportEquipmentRepository;

    public SportEquipmentController(SportEquipmentRepository sportEquipmentRepository) {
        this.sportEquipmentRepository = sportEquipmentRepository;
    }

    @GetMapping("/sportequipment")
    public Collection<SportEquipment> SportEquipments() {
        return sportEquipmentRepository.findAll().stream().collect(Collectors.toList());
    }

}
