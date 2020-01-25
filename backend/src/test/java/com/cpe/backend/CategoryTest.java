package com.cpe.backend;

import com.cpe.backend.Sportequipment.entity.Category;
import com.cpe.backend.Sportequipment.entity.Sportequipment;
import com.cpe.backend.Sportequipment.repository.CategoryRepository;

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
public class CategoryTest {
    private Validator validator;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
void b6020163_testCategoryInsertFullDataOK() {
    Category category = new Category();
    category.setCategory_name("กีฬากลางแจ้ง");
    
    category = categoryRepository.saveAndFlush(category);

    Optional<Category> found = categoryRepository.findById(category.getId());
    assertEquals(category.getCategory_name(), found.get().getCategory_name());
    
}
@Test
void b6020163_testCategoryNameMustNotBeNull() {
    Category category = new Category();
    category.setCategory_name(null);
    
    Set<ConstraintViolation<Category>> result = validator.validate(category);
    assertEquals(1, result.size());
    assertEquals("must not be null", result.iterator().next().getMessage());
    assertEquals("category_name", result.iterator().next().getPropertyPath().toString());
}
@Test
void b6020163_testCategoryMustBeGreaterEqual5() {
    Category category = new Category();
    category.setCategory_name("กีฬา");

    Set<ConstraintViolation<Category>> result = validator.validate(category);
    assertEquals(1, result.size());
    assertEquals("size must be between 5 and 20", result.iterator().next().getMessage());
    assertEquals("category_name", result.iterator().next().getPropertyPath().toString());
}
@Test
void b6020163_testBrandMustBeGreaterEqual20() {
    Category category = new Category();
    category.setCategory_name("กีฬากลางแจ้งกีฬากลางแจ้ง");

    Set<ConstraintViolation<Category>> result = validator.validate(category);
    assertEquals(1, result.size());
    assertEquals("size must be between 5 and 20", result.iterator().next().getMessage());
    assertEquals("category_name", result.iterator().next().getPropertyPath().toString());
}

}