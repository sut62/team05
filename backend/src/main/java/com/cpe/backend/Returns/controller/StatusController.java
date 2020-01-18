package com.cpe.backend.Returns.controller;
import com.cpe.backend.Returns.entity.Status;
import com.cpe.backend.Returns.repository.StatusRepository;
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
public class StatusController {
    @Autowired
    private final StatusRepository statusRepository;

    public StatusController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @GetMapping("/status")
    public Collection<Status> Statuss() {
        return statusRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/status/{id}")
    public Optional<Status> Statuss(@PathVariable Long id) {
        Optional<Status> status = statusRepository.findById(id);
        return status;
    }

}