package com.cpe.backend.Reservation.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import com.cpe.backend.Reservation.entity.Reservation;
import com.cpe.backend.Reservation.entity.Fieldtype;
import com.cpe.backend.Reservation.entity.Fielduse;
import com.cpe.backend.Reservation.entity.Member;
import com.cpe.backend.Reservation.entity.Employee;

import com.cpe.backend.Reservation.repository.ReservationRepository;
import com.cpe.backend.Reservation.repository.FieldtypeRepository;
import com.cpe.backend.Reservation.repository.FielduseRepository;
import com.cpe.backend.Reservation.repository.MemberRepository;
import com.cpe.backend.Reservation.repository.EmployeeRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ReservationController {
    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    private FieldtypeRepository fieldtypeRepository;
    @Autowired
    private FielduseRepository fielduseRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/reservation")
    public Collection<Reservation> Reservations() {
        return reservationRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/reservation/{id}/{Fieldtype_id}/{Fielduse_id}/{date}/{Stat_time}/{End_time}/{emp_id}")
    public Reservation newReservation(Reservation newReservation,
        @PathVariable long id,
        @PathVariable long Fieldtype_id,
        @PathVariable long Fielduse_id, 
        @PathVariable Date date,
        @PathVariable Date Stat_time, 
        @PathVariable Date End_time, 
        @PathVariable long emp_id) {

        Fieldtype fieldtype = fieldtypeRepository.findById(Fieldtype_id);
        Fielduse fielduse = fielduseRepository.findById(Fielduse_id);
        Member member = memberRepository.findById(id);
        Employee employee = employeeRepository.findById(emp_id);
        //Reservation reservation = ReservationRepository.findById(date(now));
        //Reservation reservation = ReservationRepository.findById(Stat_time(now));
       // Reservation reservation = ReservationRepository.findById(End_time(now));

        newReservation.setFieldtype(fieldtype);
        newReservation.setFielduse(fielduse);
        newReservation.setMember(member);
        newReservation.setEmployee(employee);
        newReservation.setDate(date);
        newReservation.setDate(Stat_time);
        newReservation.setDate(End_time);

        return reservationRepository.save(newReservation);

    }
}