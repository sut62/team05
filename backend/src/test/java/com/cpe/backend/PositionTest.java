package com.cpe.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.cpe.backend.Employee.entity.Position;
import com.cpe.backend.Employee.repository.PositionRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PositionTest {
    private Validator validator;

    @Autowired
    private PositionRepository positionRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6008031_testPositionInsertFullDataOK() {
        // สร้าง object Position
        Position position = new Position();
        // ใส่ค่าที่ถูก
        position.setPosition("พนักงานประจําเคาน์เตอร์");
        // บันทึกค่า
        position = positionRepository.saveAndFlush(position);
        // ดึงค่าที่บันทึกมา
        Optional<Position> found = positionRepository.findById(position.getPosition_id());
        // นำค่าที่ดึงมา เทียบกับค่าที่ส่งไป ว่าเหมือนกันไหม
        assertEquals(position.getPosition(), found.get().getPosition());
    }
    @Test
    void b6007690_testPositionMustNotBeNull() {
        // สร้าง object Position
        Position position = new Position();
        // ใส่ค่า null
        position.setPosition(null);
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Position>> result = validator.validate(position);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("position", result.iterator().next().getPropertyPath().toString());
    }
    

    

}