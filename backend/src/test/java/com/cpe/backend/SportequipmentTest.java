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
void b6020163_testSportequipmentInsertFullDataOK() {
    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand("Adidas");
    sportequipment.setPrice("590 บาท");
    java.sql.Date date = new java.sql.Date(2020-02-05);
    sportequipment.setDate(date);
    sportequipment.setName("ลูกวอลเลย์บอล");
    sportequipment = sportequipmentRepository.saveAndFlush(sportequipment);

    Optional<Sportequipment> found = sportequipmentRepository.findById(sportequipment.getId());
    assertEquals(sportequipment.getBrand(), found.get().getBrand());
    assertEquals(sportequipment.getPrice(), found.get().getPrice());
    assertEquals(sportequipment.getDate(), found.get().getDate());
    assertEquals(sportequipment.getSe_name(), found.get().getSe_name());
}
@Test
void b6020163_testBrandforSportequipmentMustNotBeNull() {
    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand(null);
    sportequipment.setPrice("590 บาท");
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
    sportequipment.setPrice("590 บาท");
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
    sportequipment.setPrice("590 บาท");
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
    sportequipment.setPrice("590 บาท");
    java.sql.Date date = new java.sql.Date(2020-02-05);
    sportequipment.setDate(date);
    sportequipment.setName("ลูกวอลเลย์บอล");

        Set<ConstraintViolation<Sportequipment>> result = validator.validate(sportequipment);
        assertEquals(1, result.size());
        ConstraintViolation<Sportequipment> v = result.iterator().next();
        assertEquals("must match \"[a-zA-Z]{1,20}$\"", v.getMessage());
        assertEquals("brand", v.getPropertyPath().toString());
    }
//========================================================================================================================
//price
@Test
void b6020163_testPriceforSportequipmentMustNotBeNull() {
    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand("adidas");
    sportequipment.setPrice(null);
    java.sql.Date date = new java.sql.Date(2020-02-05);
    sportequipment.setDate(date);
    sportequipment.setName("ลูกวอลเลย์บอล");
    
    Set<ConstraintViolation<Sportequipment>> result = validator.validate(sportequipment);
    assertEquals(1, result.size());
    assertEquals("must not be null", result.iterator().next().getMessage());
    assertEquals("price", result.iterator().next().getPropertyPath().toString());
}

//========================================================================================================================
//date
@Test
void b6020163_testDateforSportequipmentMustNotBeNull() {
    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand("adidas");
    sportequipment.setPrice("590 บาท");
    java.sql.Date date = new java.sql.Date(2020-02-05);
    sportequipment.setDate(null);
    sportequipment.setName("ลูกวอลเลย์บอล");
    
    Set<ConstraintViolation<Sportequipment>> result = validator.validate(sportequipment);
    assertEquals(1, result.size());
    assertEquals("must not be null", result.iterator().next().getMessage());
    assertEquals("date", result.iterator().next().getPropertyPath().toString());
}

//========================================================================================================================
//se_name
@Test
void b6020163_testSe_nameforSportequipmentMustNotBeNull() {
    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand("adidas");
    sportequipment.setPrice("590 บาท");
    java.sql.Date date = new java.sql.Date(2020-02-05);
    sportequipment.setDate(date);
    sportequipment.setName(null);
    
    Set<ConstraintViolation<Sportequipment>> result = validator.validate(sportequipment);
    assertEquals(1, result.size());
    assertEquals("must not be null", result.iterator().next().getMessage());
    assertEquals("se_name", result.iterator().next().getPropertyPath().toString());
}
@Test
    void b6020163_testSe_nameNotExactlyThePattern() {
    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand("adidas");
    sportequipment.setPrice("590 บาท");
    java.sql.Date date = new java.sql.Date(2020-02-05);
    sportequipment.setDate(date);
    sportequipment.setName("1254698%%%%");

        Set<ConstraintViolation<Sportequipment>> result = validator.validate(sportequipment);
        assertEquals(1, result.size());
        ConstraintViolation<Sportequipment> v = result.iterator().next();
        assertEquals("must match \"[ก-๙ a-zA-Z]*\"", v.getMessage());
        assertEquals("se_name", v.getPropertyPath().toString());
    }

}