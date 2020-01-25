package com.cpe.backend;

import com.cpe.backend.Sportequipment.entity.Category;
import com.cpe.backend.Sportequipment.entity.Sportequipment;
import com.cpe.backend.Sportequipment.entity.Sporttype;
import com.cpe.backend.Sportequipment.repository.SporttypeRepository;

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
public class SporttypeTest {
    private Validator validator;

    @Autowired
    private SporttypeRepository sporttypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
void b6020163_testSporttypeInsertFullDataOK() {
    Sporttype sporttype = new Sporttype();
    sporttype.setSport_type("กีฬากลางแจ้ง");
    
    sporttype = sporttypeRepository.saveAndFlush(sporttype);

    Optional<Sporttype> found = sporttypeRepository.findById(sporttype.getId());
    assertEquals(sporttype.getSport_type(), found.get().getSport_type());
    
}
@Test
void b6020163_testSporttypeNameMustNotBeNull() {
    Sporttype sporttype = new Sporttype();
    sporttype.setSport_type(null);
    
    Set<ConstraintViolation<Sporttype>> result = validator.validate(sporttype);
    assertEquals(1, result.size());
    assertEquals("must not be null", result.iterator().next().getMessage());
    assertEquals("sport_type", result.iterator().next().getPropertyPath().toString());
}
}