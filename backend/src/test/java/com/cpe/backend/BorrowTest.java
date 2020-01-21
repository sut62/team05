package com.cpe.backend;

import com.cpe.backend.borrow.repository.BorrowRepository;
import com.cpe.backend.borrow.entity.Borrow;
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

        newBorrow.setMembers(newMembers);
        newBorrow.setCategory(newCategory);
        newBorrow.setSportequipment(newSportequipment);

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
         assertEquals(borrow_date, found.get().getBorrow_date());
     }
   
  
   

}