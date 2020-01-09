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
import com.cpe.backend.Returns.entity.Returns;

import com.cpe.backend.Returns.repository.StatusRepository;
import com.cpe.backend.borrow.entity.Borrow;
import com.cpe.backend.borrow.repository.BorrowRepository;
import com.cpe.backend.Employee.entity.Employee;
import com.cpe.backend.Employee.repository.EmployeeRepository;
import com.cpe.backend.Members.entity.Members;
import com.cpe.backend.Members.repository.MembersRepository;
import com.cpe.backend.Returns.repository.ReturnsRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class ReturnController {
    @Autowired
    private final ReturnsRepository returnRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private MembersRepository membersRepository;
    @Autowired
    private BorrowRepository borrowRepository;

    ReturnController(ReturnsRepository returnRepository) {
        this.returnRepository = returnRepository;
    }

    // @GetMapping("/checkuser/{emails}/{passwords}")
    // public Collection<User> getCheck(@PathVariable("emails") String email,
    // @PathVariable("passwords") String password) {
    // return userRepository.findCheck(email,password);
    // }

    @GetMapping("/return")
    public Collection<Returns> Returns() {
        return returnRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/return/{id}")
    public Optional<Returns> Returns(@PathVariable Long id) {
        Optional<Returns> gender = returnRepository.findById(id);
        return gender;
    }

    // @GetMapping("/check/{as}")
    // public Collection<Return> getCheck(@PathVariable("as") String a) {
    // return returnRepository.findCheck(a);
    // }

    @PostMapping("/Returns/{employee}/{member}/{status}/{borrow}")
    public Returns newReturns(Returns newReturns, @PathVariable long employee, @PathVariable long member,
            @PathVariable long status, @PathVariable long borrow) {

        Employee employees = employeeRepository.findById(employee);
        Members members = membersRepository.findById(member);
        Status statuss = statusRepository.findById(status);
        Borrow borrows = borrowRepository.findById(borrow);

        newReturns.setEmployee(employees);
        newReturns.setMember(members);
        newReturns.setStatus(statuss);
        newReturns.setBorrow(borrows);
        newReturns.setTimeReturn(new Date());

        return returnRepository.save(newReturns);

    }

}