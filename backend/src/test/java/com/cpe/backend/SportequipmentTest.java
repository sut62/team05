package com.cpe.backend;

import com.cpe.backend.Sportequipment.entity.Sportequipment;
import com.cpe.backend.Sportequipment.repository.SportequipmentRepository;

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
public class SportequipmentTest {
    private Validator validator;

    @Autowired
    private SportequipmentRepository sportequipmentRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
   
//========================================================================================================================
//brand
@Test
void b6020163_testInsertBrandforSportequipmentOK() {
    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand("Adidas");
    sportequipment.setPrice(590);
    java.sql.Date date = new java.sql.Date(2020-02-05);
    sportequipment.setDate(date);
    sportequipment.setName("ลูกวอลเลย์บอล");
    sportequipment = sportequipmentRepository.saveAndFlush(sportequipment);

    Optional<Sportequipment> found = sportequipmentRepository.findById(sportequipment.getId());
    assertEquals(sportequipment.getBrand(), found.get().getBrand());
}
@Test
void b6020163_testBrandforSportequipmentMustNotBeNull() {
    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand(null);
    sportequipment.setPrice(590);
    java.sql.Date date = new java.sql.Date(2020-02-05);
    sportequipment.setDate(date);
    sportequipment.setName("ลูกวอลเลย์บอล");
    
    Set<ConstraintViolation<Sportequipment>> result = validator.validate(sportequipment);
    assertEquals(1, result.size());
    assertEquals("must not be null", result.iterator().next().getMessage());
    assertEquals("brand", result.iterator().next().getPropertyPath().toString());
}
@Test
void b6020163_testBrandMustBeGreaterEqual3() {
    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand("Ad");
    sportequipment.setPrice(590);
    java.sql.Date date = new java.sql.Date(2020-02-05);
    sportequipment.setDate(date);
    sportequipment.setName("ลูกวอลเลย์บอล");

    Set<ConstraintViolation<Sportequipment>> result = validator.validate(sportequipment);
    assertEquals(1, result.size());
    assertEquals("size must be between 3 and 15", result.iterator().next().getMessage());
    assertEquals("brand", result.iterator().next().getPropertyPath().toString());
}
@Test
void b6020163_testBrandMustBeGreaterEqual15() {
    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand("AdidasAdidasAdidas");
    sportequipment.setPrice(590);
    java.sql.Date date = new java.sql.Date(2020-02-05);
    sportequipment.setDate(date);
    sportequipment.setName("ลูกวอลเลย์บอล");

    Set<ConstraintViolation<Sportequipment>> result = validator.validate(sportequipment);
    assertEquals(1, result.size());
    assertEquals("size must be between 3 and 15", result.iterator().next().getMessage());
    assertEquals("brand", result.iterator().next().getPropertyPath().toString());
}
@Test
    void b6020163_testBrandNotExactlyThePattern() {
    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand("33445515555459");
    sportequipment.setPrice(590);
    java.sql.Date date = new java.sql.Date(2020-02-05);
    sportequipment.setDate(date);
    sportequipment.setName("ลูกวอลเลย์บอล");

        Set<ConstraintViolation<Sportequipment>> result = validator.validate(sportequipment);
        assertEquals(1, result.size());
        ConstraintViolation<Sportequipment> v = result.iterator().next();
        assertEquals("must match \"[a-zA-Z]{1,20}$\"", v.getMessage());
        assertEquals("brand", v.getPropertyPath().toString());
    }


}