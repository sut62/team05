package com.cpe.backend.Members.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Collection;
import java.util.stream.Collectors;


import com.cpe.backend.Members.entity.Members;
import com.cpe.backend.Members.entity.Gender;
import com.cpe.backend.Members.entity.Province;
import com.cpe.backend.Members.entity.Nametype;

import com.cpe.backend.Members.repository.MembersRepository;
import com.cpe.backend.Members.repository.NametypeRepository;
import com.cpe.backend.Members.repository.ProvinceRepository;
import com.cpe.backend.Members.repository.GenderRepository;


import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MembersController {
    @Autowired
    private final MembersRepository membersRepository;
    @Autowired
    private NametypeRepository nametypeRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private GenderRepository genderRepository;

    MembersController(MembersRepository membersRepository) {
        this.membersRepository = membersRepository;
    }

    @GetMapping("/Members")
    public Collection<Members> Members() {
        return membersRepository.findAll().stream().collect(Collectors.toList());
    }
    @PostMapping("/Members/{name}/{date}/{address}/{email}/{phonenumber}/{nametype_id}/{gender_id}/{province_id}")
    public Members newMembers(Members newMembers,
    @PathVariable String name,
    @PathVariable Date date,
    @PathVariable String address,
    @PathVariable String email,
    @PathVariable String phonenumber,
    @PathVariable long nametype_id,
    @PathVariable long gender_id,
    @PathVariable long province_id)
    
    {
    Nametype nametype = nametypeRepository.findById(nametype_id);
    Gender gender = genderRepository.findById(gender_id);
    Province province = provinceRepository.findById(province_id);


    newMembers.setName(name);
    newMembers.setDate(date);
    newMembers.setAddress(address);
    newMembers.setEmail(email);
    newMembers.setPhonenumber(phonenumber);
    newMembers.setNametype(nametype);
    newMembers.setGender(gender);
    newMembers.setProvince(province);
    return membersRepository.save(newMembers); 
    
    }
}