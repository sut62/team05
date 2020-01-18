package com.cpe.backend;

import javax.validation.Validator;

import com.cpe.backend.Returns.entity.Status;
import com.cpe.backend.Returns.repository.StatusRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;

import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

@DataJpaTest
public class BackendApplicationTests {
    private Validator validator;
    @Autowired
    private StatusRepository statusRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


}
