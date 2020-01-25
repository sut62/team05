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
        // สร้าง object Status
        Status status = new Status();
        // ใส่ค่าที่ถูก
        status.setStatuss("ปรกติ");
        // บันทึกค่า
        status = statusRepository.saveAndFlush(status);
        // ดึงค่าที่บันทึกมา
        Optional<Status> found = statusRepository.findById(status.getStatus_id());
        // นำค่าที่ดึงมา เทียบกับค่าที่ส่งไป ว่าเหมือนกันไหม
        assertEquals(status.getStatuss(), found.get().getStatuss());
    }

    @Test
    void b6002671_testStatusMustNotBeNull() {
        // สร้าง object Status
        Status status = new Status();
        // ใส่ค่า null
        status.setStatuss(null);
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Status>> result = validator.validate(status);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("statuss", result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void b6002671_testTypeMustBeGreaterEqual5() {
        // สร้าง object Status
        Status status = new Status();
        // ใส่ค่า ที่ขนาดไม่ตรงกับที่จริง (size = 4)
        status.setStatuss("ปรกต");
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Status>> result = validator.validate(status);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        assertEquals("size must be between 5 and 10", result.iterator().next().getMessage());
        assertEquals("statuss", result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void b6002671_testTypeMustBeLessEqual10() {
        // สร้าง object Status
        Status status = new Status();
        // ใส่ค่า ที่ขนาดไม่ตรงกับที่จริง (size = 11)
        status.setStatuss("เสียหายเสีย");
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Status>> result = validator.validate(status);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        assertEquals("size must be between 5 and 10", result.iterator().next().getMessage());
        assertEquals("statuss", result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void b6002671_testPattrenStatus() {
        // สร้าง object Status
        Status status = new Status();
        // ใส่ค่า ไม่ตรงกับPattren
        status.setStatuss("ปรกติA");
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Status>> result = validator.validate(status);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Status> message = result.iterator().next();
        assertEquals("must match \"[ก-เ]*\"", message.getMessage());
        assertEquals("statuss", message.getPropertyPath().toString());
    }

}