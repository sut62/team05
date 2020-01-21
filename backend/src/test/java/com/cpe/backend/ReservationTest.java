package com.cpe.backend;

import com.cpe.backend.Reservation.entity.Reservation;
import com.cpe.backend.Reservation.repository.ReservationRepository;

import com.cpe.backend.Reservation.entity.Fieldtype;
import com.cpe.backend.Reservation.repository.FieldtypeRepository;

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
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class ReservationTest {
    private Validator validator;

    @Autowired
    private ReservationRepository reservationRepository;
    private FieldtypeRepository fieldtypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
   
   
    @Test
    void b6008970_testDateResvertionOK() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        String dateInString = "31-08-1998";
        Date date = sdf.parse(dateInString);
        Reservation r = new Reservation();
        r.setDate(date);
        r = reservationRepository.saveAndFlush(r);
        Optional<Reservation> found = reservationRepository.findById(r.getReservation_id());
        System.out.println(found.get().getDate());
        assertEquals(date, found.get().getDate());
        assertEquals(sdf.parse("31-08-1998"), found.get().getDate());
    }
    @Test
    void b6008970_testDateResvertionNotNull() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        String dateInString = "31-08-1998";
        Date date = sdf.parse(dateInString);
        Reservation r = new Reservation();
        r.setDate(null);
        Set<ConstraintViolation<Reservation>> result = validator.validate(r);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("date", result.iterator().next().getPropertyPath().toString());
    }
    @Test
    void b6008970_testFieldtypeNotnull() {
        Fieldtype fieldtype = new Fieldtype();
        fieldtype.setFieldtype_name(null);
        Set<ConstraintViolation<Fieldtype>> result = validator.validate(fieldtype);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("Fieldtype_name", result.iterator().next().getPropertyPath().toString());
    }
    @Test
    void b6008970_testTypeMustGreaterEqual4() {
        Fieldtype fieldtype = new Fieldtype();
        fieldtype.setFieldtype_name("123");
        Set<ConstraintViolation<Fieldtype>> result = validator.validate(fieldtype);
        assertEquals(1, result.size());
        assertEquals("size must be between 4 and 30", result.iterator().next().getMessage());
        assertEquals("Fieldtype_name", result.iterator().next().getPropertyPath().toString());
    }
    @Test
    void b6008970_testTypeMustLessEqual30() {
        Fieldtype fieldtype = new Fieldtype();
        fieldtype.setFieldtype_name("1234567890123456789012345678901");
        Set<ConstraintViolation<Fieldtype>> result = validator.validate(fieldtype);
        assertEquals(1, result.size());
        assertEquals("size must be between 4 and 30", result.iterator().next().getMessage());
        assertEquals("Fieldtype_name", result.iterator().next().getPropertyPath().toString());
    }
   

}