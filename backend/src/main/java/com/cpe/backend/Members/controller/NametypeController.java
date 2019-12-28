package com.cpe.backend.Members.controller;

import com.cpe.backend.Members.entity.Nametype;
import com.cpe.backend.Members.repository.NametypeRepository;

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
public class NametypeController {

    @Autowired
    private final NametypeRepository nametypeRepository;

    public NametypeController(NametypeRepository nametypeRepository) {
        this.nametypeRepository = nametypeRepository;
    }

    @GetMapping("/nametype")
    public Collection<Nametype> Nametype() {
        return nametypeRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/nametype/{id}")
    public Optional<Nametype> Nametype(@PathVariable Long id) {
        Optional<Nametype> nametype = nametypeRepository.findById(id);
        return nametype;
    }

}