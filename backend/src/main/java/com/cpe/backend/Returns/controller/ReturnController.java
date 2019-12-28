package com.cpe.backend.Returns.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cpe.backend.Returns.entity.Status;
import com.cpe.backend.Returns.entity.Return;

import com.cpe.backend.Returns.repository.StatusRepository;
import com.cpe.backend.Returns.repository.ReturnRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class ReturnController {
    @Autowired
    private final ReturnRepository returnRepository;
    @Autowired
    private StatusRepository statusRepository;


    ReturnController(ReturnRepository returnRepository) {
        this.returnRepository = returnRepository;
    }

    // @GetMapping("/checkuser/{emails}/{passwords}")
    // public Collection<User> getCheck(@PathVariable("emails") String email, @PathVariable("passwords") String password) {
    //     return userRepository.findCheck(email,password);
    // }

    @GetMapping("/return")
    public Collection<Return> Returns() {
        return returnRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/return/{id}")
        public Optional<Return> Returns(@PathVariable Long id) {
            Optional<Return> gender = returnRepository.findById(id);
            return gender;
    }

    // @GetMapping("/check/{as}")
    // public Collection<Return> getCheck(@PathVariable("as") String a) {
    //     return returnRepository.findCheck(a);
    // }

  
    // @PostMapping("/User/{names}/{emails}/{passwords}/{phones}/{gender_id}/{nametype_id}/{phonetype_id}")
    // public User newUser(User newUser,
    // @PathVariable String names,
    // @PathVariable String emails,
    // @PathVariable String passwords,
    // @PathVariable String phones,
    // @PathVariable long gender_id,
    // @PathVariable long nametype_id,
    // @PathVariable long phonetype_id) {
    
    // Gender gender = genderRepository.findById(gender_id);
    // NameType nametype = nametypeRepository.findById(nametype_id);
    // PhoneType phonetype = phonetypeRepository.findById(phonetype_id);

    // newUser.setName(names);
    // newUser.setEmail(emails);
    // newUser.setPassword(passwords);
    // newUser.setPhone(phones);
    // newUser.setGender(gender);
    // newUser.setNameType(nametype);
    // newUser.setPhoneType(phonetype);
    // newUser.setRegistertime(new Date());

    // return userRepository.save(newUser);
    
    // }
}