package com.cpe.backend;

import com.cpe.backend.Reservation.entity.Reservation;
import com.cpe.backend.Reservation.repository.ReservationRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class ReservationTest {
    private Validator validator;

    @Autowired
    private ReservationRepository reservationRepository;



    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
   
   //   test การใส่ข้อมูลครบของ Reservation
    @Test
    void b6008970_testResvertionOK() {
        long milli = 123456789999l; 
        java.sql.Date date = new java.sql.Date(2020-02-05);
        java.sql.Time time = new java.sql.Time(milli);
        Reservation r = new Reservation();
        r.setDate(date);
        r.setStart_time(time);
        r.setEnd_time(time);
        r = reservationRepository.saveAndFlush(r);
        Optional<Reservation> found = reservationRepository.findById(r.getReservation_id());
        assertEquals(r.getDate(), found.get().getDate());
        assertEquals(r.getStart_time(), found.get().getStart_time());
        assertEquals(r.getEnd_time(), found.get().getEnd_time());
    }
     //   test การใส่ข้อมูลdateต้องไม่เป็น Notnull ของ Reservation
    @Test
    void b6008970_testDateResvertionNotNull() {
        Reservation r = new Reservation();
        long milli = 123456789999l;
        java.sql.Date date = new java.sql.Date(2020-02-05);
        java.sql.Time time = new java.sql.Time(milli);
        r.setDate(null);
        r.setStart_time(time);
        r.setEnd_time(time);
        Set<ConstraintViolation<Reservation>> result = validator.validate(r);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("date", result.iterator().next().getPropertyPath().toString());
    }
     //   test การใส่ข้อมูล start_time ต้องไม่เป็น Notnull ของ Reservation
    @Test
    void b6008970_testStarttimeResvertionNotNull() throws ParseException {
        Reservation r = new Reservation();
        long milli = 123456789999l;
        java.sql.Date date = new java.sql.Date(2020-02-05);
        java.sql.Time time = new java.sql.Time(milli);
        r.setDate(date);
        r.setStart_time(null);
        r.setEnd_time(time);
        Set<ConstraintViolation<Reservation>> result = validator.validate(r);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("Start_time", result.iterator().next().getPropertyPath().toString());
    }
    //   test การใส่ข้อมูล start_time ต้องไม่เป็น Notnull ของ Reservation
    @Test
    void b6008970_testEndtimeResvertionNotNull() throws ParseException {
        Reservation r = new Reservation();
        long milli = 123456789999l;
        java.sql.Date date = new java.sql.Date(2020-02-05);
        java.sql.Time time = new java.sql.Time(milli);
        r.setDate(date);
        r.setStart_time(time);
        r.setEnd_time(null);
        Set<ConstraintViolation<Reservation>> result = validator.validate(r);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("End_time", result.iterator().next().getPropertyPath().toString());
    }
    
   

}