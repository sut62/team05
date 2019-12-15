package com.cpe.backend.borrow.controller;

import com.cpe.backend.borrow.entity.Members;
import com.cpe.backend.borrow.repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MembersController {
    @Autowired
    private final MembersRepository membersRepository;

    public MembersController(MembersRepository membersRepository) {
        this.membersRepository = membersRepository;
    }

    @GetMapping("/members")
    public Collection<Members> Memberss() {
        return membersRepository.findAll().stream().collect(Collectors.toList());
    }

}
