package com.cpe.backend.Sportequipment.controller;
import com.cpe.backend.Sportequipment.entity.Sporttype;
import com.cpe.backend.Sportequipment.repository.SporttypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class SporttypeController {
   @Autowired
    private final SporttypeRepository sporttypeRepository;

    public SporttypeController(SporttypeRepository sporttypeRepository) {
        this.sporttypeRepository = sporttypeRepository;
    }

    @GetMapping("/sporttype")
    public Collection<Sporttype> Sporttype() {
        return sporttypeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/sporttype/{id}")
    public Optional<Sporttype> Sporttype(@PathVariable Long id) {
        Optional<Sporttype> sporttype = sporttypeRepository.findById(id);
        return sporttype;
    }

}