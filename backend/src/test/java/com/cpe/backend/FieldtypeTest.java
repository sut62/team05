package com.cpe.backend;

import com.cpe.backend.Reservation.entity.Fieldtype;
import com.cpe.backend.Reservation.repository.FieldtypeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import javax.persistence.RollbackException;
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
public class FieldtypeTest {
    private Validator validator;

    @Autowired
    private FieldtypeRepository fieldtypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6002671_testInsertFieldtypeOK() {
        Fieldtype fieldtype = new Fieldtype();
        fieldtype.setFieldtype_name("สนามกรีฑา");
        fieldtype = fieldtypeRepository.saveAndFlush(fieldtype);
        Optional<Fieldtype> found = fieldtypeRepository.findById(fieldtype.getFieldtype_id());
        assertEquals(fieldtype.getFieldtype_name(), found.get().getFieldtype_name());
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
    void b6008970_testFieldtypeTypeMustGreaterEqual9() {
        Fieldtype fieldtype = new Fieldtype();
        fieldtype.setFieldtype_name("สนามกรีฑ");
        Set<ConstraintViolation<Fieldtype>> result = validator.validate(fieldtype);
        assertEquals(1, result.size());
        assertEquals("size must be between 9 and 21", result.iterator().next().getMessage());
        assertEquals("Fieldtype_name", result.iterator().next().getPropertyPath().toString());
    }
    @Test
    void b6008970_testFieldtypeTypeMustLessEqual21() {
        Fieldtype fieldtype = new Fieldtype();
        fieldtype.setFieldtype_name("สนามวอลเล่ย์บอลชายหาดด");
        Set<ConstraintViolation<Fieldtype>> result = validator.validate(fieldtype);
        assertEquals(1, result.size());
        assertEquals("size must be between 9 and 21", result.iterator().next().getMessage());
        assertEquals("Fieldtype_name", result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void b6008970_testPattrenFieldtype(){
        Fieldtype fieldtype = new Fieldtype();
        fieldtype.setFieldtype_name("สนามกรีฑาAza๑");
        Set<ConstraintViolation<Fieldtype>> result = validator.validate(fieldtype);
        assertEquals(1, result.size());
        assertEquals("must match \"^[ก-๙เ]*$\"",result.iterator().next().getMessage());
        assertEquals("Fieldtype_name",result.iterator().next().getPropertyPath().toString());
    }

    
}