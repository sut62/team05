package com.cpe.backend;

import com.cpe.backend.borrow.repository.BorrowRepository;
import com.cpe.backend.borrow.entity.Borrow;
import com.cpe.backend.Employee.entity.Employee;
import com.cpe.backend.Employee.repository.EmployeeRepository;
import com.cpe.backend.Members.entity.Members;
import com.cpe.backend.Members.repository.MembersRepository;
import com.cpe.backend.Sportequipment.entity.Category;
import com.cpe.backend.Sportequipment.repository.CategoryRepository;
import com.cpe.backend.Sportequipment.entity.Sportequipment;
import com.cpe.backend.Sportequipment.repository.SportequipmentRepository;

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
public class BorrowTest {
    private Validator validator;

    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private MembersRepository membersRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SportequipmentRepository sportequipmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6002664_testBorrowInsertDataOK() {
        Borrow newBorrow = new Borrow();
        Members newMembers = membersRepository.findById(1);
        Category newCategory = categoryRepository.findById(1);
        Sportequipment newSportequipment = sportequipmentRepository.findById(1);
        Employee newEmployee = employeeRepository.findById(1);


        newBorrow.setMembers(newMembers);
        newBorrow.setCategory(newCategory);
        newBorrow.setSportequipment(newSportequipment);
        newBorrow.setEmployee(newEmployee);

        String datetime = "2020-01-21 15:03:45";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date borrow_date = new Date();
        try {
            borrow_date = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newBorrow.setBorrow_date(borrow_date);

        newBorrow = borrowRepository.saveAndFlush(newBorrow);

        Optional<Borrow> found = borrowRepository.findById(newBorrow.getBorrow_id());
        assertEquals(newMembers, found.get().getMembers());
        assertEquals(newCategory, found.get().getCategory());
        assertEquals(newSportequipment, found.get().getSportequipment());
        assertEquals(newEmployee, found.get().getEmployee());
        assertEquals(borrow_date, found.get().getBorrow_date());
    }

    @Test
    void B6002664_testBorrow_CategoryMustNotBeNull() {
        Borrow newBorrow = new Borrow();
        Members newMembers = membersRepository.findById(1);
        Category newCategory = categoryRepository.findById(1);
        Sportequipment newSportequipment = sportequipmentRepository.findById(1);
        Employee newEmployee = employeeRepository.findById(1);

        newBorrow.setMembers(newMembers);
        newBorrow.setCategory(null);
        newBorrow.setSportequipment(newSportequipment);
        newBorrow.setEmployee(newEmployee);


        String datetime = "2020-01-21 15:03:45";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date borrow_date = new Date();
        try {
            borrow_date = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }

        newBorrow.setBorrow_date(borrow_date);

        Set<ConstraintViolation<Borrow>> result = validator.validate(newBorrow);
        // ต้องมี 1 Error
        ConstraintViolation<Borrow> v = result.iterator().next();
        // error message ตรงชนิด และ ถูก field
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("category", result.iterator().next().getPropertyPath().toString());

    } @Test
    void B6002664_testBorrow_DateMustNotBeNull() {
        Borrow newBorrow = new Borrow();
        Members newMembers = membersRepository.findById(1);
        Category newCategory = categoryRepository.findById(1);
        Sportequipment newSportequipment = sportequipmentRepository.findById(1);
        Employee newEmployee = employeeRepository.findById(1);

        newBorrow.setMembers(newMembers);
        newBorrow.setCategory(newCategory);
        newBorrow.setSportequipment(newSportequipment);
        newBorrow.setEmployee(newEmployee);


        String datetime = "2020-01-21 15:03:45";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date borrow_date = new Date();
        try {
            borrow_date = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }

        newBorrow.setBorrow_date(null);

        Set<ConstraintViolation<Borrow>> result = validator.validate(newBorrow);
        // ต้องมี 1 Error
        ConstraintViolation<Borrow> v = result.iterator().next();
        // error message ตรงชนิด และ ถูก field
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("borrow_date", result.iterator().next().getPropertyPath().toString());

    }
    
    

}