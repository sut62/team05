package com.cpe.backend;

import com.cpe.backend.Reservation.entity.Fielduse;
import com.cpe.backend.Reservation.repository.FielduseRepository;

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
public class FielduseTest {
    private Validator validator;

    @Autowired
    private FielduseRepository fielduseRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6002671_testInsertFielduseOK() {
        Fielduse fielduse = new Fielduse();
        fielduse.setFielduse_name("ปกติ");
        fielduse = fielduseRepository.saveAndFlush(fielduse);
        Optional<Fielduse> found = fielduseRepository.findById(fielduse.getFielduse_id());
        assertEquals(fielduse.getFielduse_name(), found.get().getFielduse_name());
    }
    @Test
    void b6008970_testFielduseNotnull() {
        Fielduse fielduse = new Fielduse();
        fielduse.setFielduse_name(null);
        Set<ConstraintViolation<Fielduse>> result = validator.validate(fielduse);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("Fielduse_name", result.iterator().next().getPropertyPath().toString());
    }
    @Test
    void b6008970_testFielduseTypeMustGreaterEqual4() {
        Fielduse fielduse = new Fielduse();
        fielduse.setFielduse_name("ปกต");
        Set<ConstraintViolation<Fielduse>> result = validator.validate(fielduse);
        assertEquals(1, result.size());
        assertEquals("size must be between 4 and 10", result.iterator().next().getMessage());
        assertEquals("Fielduse_name", result.iterator().next().getPropertyPath().toString());
    }
    @Test
    void b6008970_testFielduseTypeMustLessEqual8() {
        Fielduse fielduse = new Fielduse();
        fielduse.setFielduse_name("แข่งขันนนนน");
        Set<ConstraintViolation<Fielduse>> result = validator.validate(fielduse);
        assertEquals(1, result.size());
        assertEquals("size must be between 4 and 10", result.iterator().next().getMessage());
        assertEquals("Fielduse_name", result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void b6008970_testPattrenFielduse(){
        Fielduse fielduse = new Fielduse();
        fielduse.setFielduse_name("แข่งขัA๘");
        Set<ConstraintViolation<Fielduse>> result = validator.validate(fielduse);
        assertEquals(1, result.size());
        ConstraintViolation<Fielduse> message = result.iterator().next();
        assertEquals("must match \"^[ก-๙เ]*$\"",message.getMessage());
        assertEquals("Fielduse_name",message.getPropertyPath().toString());
    }

    
}