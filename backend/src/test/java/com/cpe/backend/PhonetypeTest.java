package com.cpe.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.cpe.backend.Employee.entity.Phonetype;
import com.cpe.backend.Employee.repository.PhonetypeRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PhonetypeTest {
    private Validator validator;

    @Autowired
    private PhonetypeRepository phonetypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6008031_testPhonetypeInsertFullDataOK() {
        // สร้าง object Phonetype
        Phonetype phonetype = new Phonetype();
        // ใส่ค่าที่ถูก
        phonetype.setPhonetype("มือถือส่วนตัว");
        // บันทึกค่า
        phonetype = phonetypeRepository.saveAndFlush(phonetype);
        // ดึงค่าที่บันทึกมา
        Optional<Phonetype> found = phonetypeRepository.findById(phonetype.getPhonetype_id());
        // นำค่าที่ดึงมา เทียบกับค่าที่ส่งไป ว่าเหมือนกันไหม
        assertEquals(phonetype.getPhonetype(), found.get().getPhonetype());
    }
    @Test
    void b6007690_testPhonetypeMustNotBeNull() {
        // สร้าง object Phonetype
        Phonetype phonetype = new Phonetype();
        // ใส่ค่า null
        phonetype.setPhonetype(null);
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Phonetype>> result = validator.validate(phonetype);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("phonetype", result.iterator().next().getPropertyPath().toString());
    }
    

    

}