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

import lombok.Data;
import lombok.NonNull;

import java.io.IOException;
import java.util.Collection;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import java.sql.Date;
import java.sql.Time;

import com.cpe.backend.Reservation.entity.Reservation;
import com.cpe.backend.Reservation.entity.Fieldtype;
import com.cpe.backend.Reservation.entity.Fielduse;
import com.cpe.backend.Members.entity.Members;
import com.cpe.backend.Employee.entity.Employee;

import com.cpe.backend.Reservation.repository.ReservationRepository;
import com.cpe.backend.Reservation.repository.FieldtypeRepository;
import com.cpe.backend.Reservation.repository.FielduseRepository;
import com.cpe.backend.Members.repository.MembersRepository;
import com.cpe.backend.Employee.repository.EmployeeRepository;

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
    private MembersRepository membersRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/reservation")
    public Collection<Reservation> Reservations() {
        return reservationRepository.findAll().stream().collect(Collectors.toList());
    }

    // @PostMapping("/reservation/{member}/{Fieldtype_id}/{Fielduse_id}/{date}/{Start_time}/{End_time}/{emp_id}")
    // public Reservation newReservation(Reservation newReservation,
    // @PathVariable long member,
    // @PathVariable long Fieldtype_id,
    // @PathVariable long Fielduse_id,
    // @PathVariable long emp_id,
    // @PathVariable Date date,
    // @PathVariable Date Start_time,
    // @PathVariable Date End_time
    // ) {

    @PostMapping("/reservation/{member}/{Fieldtype_id}/{Fielduse_id}/{start_time}/{end_time}/{date}/{emp_id}")
    public Reservation newReservation(Reservation newReservation, @PathVariable long member,
            @PathVariable long Fieldtype_id, @PathVariable long Fielduse_id, @PathVariable long emp_id,
            @PathVariable Date date, @PathVariable Time start_time, @PathVariable Time end_time) {

        Fieldtype fieldtype = fieldtypeRepository.findById(Fieldtype_id);
        Fielduse fielduse = fielduseRepository.findById(Fielduse_id);
        Members members = membersRepository.findById(member);
        Employee employee = employeeRepository.findById(emp_id);

        newReservation.setFieldtype(fieldtype);
        newReservation.setFielduse(fielduse);
        newReservation.setMembers(members);
        newReservation.setEmployee(employee);
        newReservation.setDate(date);
        newReservation.setStart_time(start_time);
        newReservation.setEnd_time(end_time);

        return reservationRepository.save(newReservation);

    }
}