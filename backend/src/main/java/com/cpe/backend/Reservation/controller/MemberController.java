package com.cpe.backend.Reservation.controller;

import com.cpe.backend.Reservation.entity.Member;
import com.cpe.backend.Reservation.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MemberController {

    @Autowired
    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/member")
    public Collection<Member> Members() {
        return memberRepository.findAll().stream().collect(Collectors.toList());
    }

}