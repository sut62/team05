package com.cpe.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.cpe.backend.Members.entity.Province;
import com.cpe.backend.Members.repository.ProvinceRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProvinceTest {
    private Validator validator;

    @Autowired
    private ProvinceRepository provinceRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6007690_testInsertProvinceOK() {
        // สร้าง object Status
        Province province = new Province();
        // ใส่ค่าที่ถูก
        province.setProvince("นครสวรรค์");
        // บันทึกค่า
        province = provinceRepository.saveAndFlush(province);
        // ดึงค่าที่บันทึกมา
        Optional<Province> found = provinceRepository.findById(province.getProvince_id());
        // นำค่าที่ดึงมา เทียบกับค่าที่ส่งไป ว่าเหมือนกันไหม
        assertEquals(province.getProvince(), found.get().getProvince());
    }
    @Test
    void b6007690_testProvinceMustNotBeNull() {
        // สร้าง object Status
        Province province = new Province();
        // ใส่ค่า null
        province.setProvince(null);
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Province>> result = validator.validate(province);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("province", result.iterator().next().getPropertyPath().toString());
    }
    

    

}