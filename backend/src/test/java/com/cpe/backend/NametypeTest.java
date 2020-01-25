package com.cpe.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.cpe.backend.Members.entity.Nametype;
import com.cpe.backend.Members.repository.NametypeRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class NametypeTest {
    private Validator validator;

    @Autowired
    private NametypeRepository nametypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6007690_testInsertNametypeOK() {
        // สร้าง object Status
        Nametype nametype = new Nametype();
        // ใส่ค่าที่ถูก
        nametype.setNametype("นาย");
        // บันทึกค่า
        nametype = nametypeRepository.saveAndFlush(nametype);
        // ดึงค่าที่บันทึกมา
        Optional<Nametype> found = nametypeRepository.findById(nametype.getNametype_id());
        // นำค่าที่ดึงมา เทียบกับค่าที่ส่งไป ว่าเหมือนกันไหม
        assertEquals(nametype.getNametype(), found.get().getNametype());
    }
    @Test
    void b6007690_testNametypeMustNotBeNull() {
        // สร้าง object Status
        Nametype nametype = new Nametype();
        // ใส่ค่า null
        nametype.setNametype(null);
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Nametype>> result = validator.validate(nametype);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("nametype", result.iterator().next().getPropertyPath().toString());
    }
    

    

}