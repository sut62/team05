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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


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

    
    } @Test
    void b6002664_testBorrow_datemustbaDateintehPastorPresent() throws ParseException {
        // สร้าง object Borrow
         Borrow newBorrow = new Borrow();
        
        // กำหนด Pattern ของวัน
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // ใส่ค่าที่เรากำหนดไว้
        LocalDate dataDate = LocalDate.parse((String) "2023-08-12", dateFormat);
        // ใส่ค่าที่เรากำหนดไว้
        newBorrow.setBorrow_date(dataDate);
        newBorrow.setNote("ยืมไปเล่นในโรงยิม");
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Borrow>> result = validator.validate(newBorrow);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Borrow> v = result.iterator().next();
        assertEquals("must be a date in the past or in the present", v.getMessage());
        assertEquals("borrow_date", v.getPropertyPath().toString());
    }
    @Test
    void b6002664_testInsertBorrowOK() {
        // สร้าง object Borrow
        Borrow newBorrow = new Borrow();
        // กำหนด Pattern ของวัน
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // ใส่เวลา เป็น string ใน ให้ตรงกับ PattrenFormat ด้านบน
        LocalDate dataDate = LocalDate.parse((String) "2020-01-01", dateFormat);
        // ใส่ค่าที่เรากำหนดไว้
       newBorrow.setBorrow(dataDate);
       newBorrow.setNote("ยืมไปเล่นในโรงยิม");
        // บันทึกค่า ใน Repository
        newBorrow = borrowRepository.saveAndFlush(newBorrow);
        
        // ดึงค่าที่บันทึกมา
        Optional<Borrow> found = borrowRepository.findById(newBorrow.getBorrow_id());
        // นำค่าที่ดึงมา เทียบกับค่าที่ส่งไป ว่าเหมือนกันไหม
        assertEquals(dataDate, found.get().getBorrow_date());

    }
    @Test
    void b6002664_testBorrow_dateMustNotBeNull() {
        // สร้าง object Borrow
        Borrow newBorrow = new Borrow();
        // ใส่ค่า null 
       newBorrow.setBorrow_date(null);
       newBorrow.setNote("ยืมไปเล่นในโรงยิม");
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Borrow>> result = validator.validate(newBorrow);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Borrow> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("borrow_date", v.getPropertyPath().toString());
    }
    @Test
    void b6002664_testNoteBeGreaterEqual20() {
        // สร้าง object Borrow
        Borrow newBorrow = new Borrow();
         // กำหนด Pattern ของวัน
         DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         // ใส่เวลา เป็น string ใน ให้ตรงกับ PattrenFormat ด้านบน
         LocalDate dataDate = LocalDate.parse((String) "2020-01-01", dateFormat);
        newBorrow.setBorrow_date(dataDate);
        newBorrow.setNote("ยืมไปเล่นในโรงยิมกับเพื่อนๆ");
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Borrow>> result = validator.validate(newBorrow);
        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 20", result.iterator().next().getMessage());
        assertEquals("note", result.iterator().next().getPropertyPath().toString());
    }
    


   
    

}