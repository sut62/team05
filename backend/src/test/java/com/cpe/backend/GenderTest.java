package com.cpe.backend;

import com.cpe.backend.Members.entity.Gender;
import com.cpe.backend.Members.repository.GenderRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class GenderTest {
    private Validator validator;

    @Autowired
    private GenderRepository genderRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6007690_testInsertGenderOK() {
        // สร้าง object Status
        Gender gender = new Gender();
        // ใส่ค่าที่ถูก
        gender.setGender("ชาย");
        // บันทึกค่า
        gender = genderRepository.saveAndFlush(gender);
        // ดึงค่าที่บันทึกมา
        Optional<Gender> found = genderRepository.findById(gender.getGender_id());
        // นำค่าที่ดึงมา เทียบกับค่าที่ส่งไป ว่าเหมือนกันไหม
        assertEquals(gender.getGender(), found.get().getGender());
    }
    @Test
    void b6007690_testGenderMustNotBeNull() {
        // สร้าง object Status
        Gender gender = new Gender();
        // ใส่ค่า null
        gender.setGender(null);
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Gender>> result = validator.validate(gender);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("gender", result.iterator().next().getPropertyPath().toString());
    }
    

    @Test
    void b6007690_testGenderMustBeGreaterEqual5() {
        // สร้าง object Status
        Gender gender = new Gender();
        // ใส่ค่า null
        gender.setGender("ชา");
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Gender>> result = validator.validate(gender);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        assertEquals("size must be between 3 and 10", result.iterator().next().getMessage());
        assertEquals("gender", result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void b6007690_testGenderMustBeGreaterEqual10() {
        // สร้าง object Status
        Gender gender = new Gender();
        // ใส่ค่า null
        gender.setGender("ชายชายชายชายชาย");
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Gender>> result = validator.validate(gender);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        assertEquals("size must be between 3 and 10", result.iterator().next().getMessage());
        assertEquals("gender", result.iterator().next().getPropertyPath().toString());
    }

}