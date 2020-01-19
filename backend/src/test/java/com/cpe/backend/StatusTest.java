package com.cpe.backend;

import com.cpe.backend.Returns.entity.Status;
import com.cpe.backend.Returns.repository.StatusRepository;

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
public class StatusTest {
    private Validator validator;

    @Autowired
    private StatusRepository statusRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6002671_testInsertStatusOK() {
        Status status = new Status();
        status.setStatuss("ปรกติ");
        status = statusRepository.saveAndFlush(status);
        Optional<Status> found = statusRepository.findById(status.getStatus_id());
        assertEquals(status.getStatuss(), found.get().getStatuss());
    }

    @Test
    void b6002671_testStatusMustNotBeNull() {
        Status status = new Status();
        status.setStatuss(null);
        Set<ConstraintViolation<Status>> result = validator.validate(status);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("statuss", result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void b6002671_testTypeMustBeGreaterEqual5() {
        Status status = new Status();
        status.setStatuss("1234");
        Set<ConstraintViolation<Status>> result = validator.validate(status);
        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 10", result.iterator().next().getMessage());
        assertEquals("statuss", result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void b6002671_testTypeMustBeLessEqual10() {
        Status status = new Status();
        status.setStatuss("12345678901");
        Set<ConstraintViolation<Status>> result = validator.validate(status);
        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 10", result.iterator().next().getMessage());
        assertEquals("statuss", result.iterator().next().getPropertyPath().toString());
    }

}