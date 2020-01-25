package com.cpe.backend;

import com.cpe.backend.Employee.entity.Employee;
import com.cpe.backend.Employee.repository.EmployeeRepository;
import com.cpe.backend.Members.entity.Members;
import com.cpe.backend.Members.repository.MembersRepository;
import com.cpe.backend.Returns.entity.Returns;
import com.cpe.backend.Returns.repository.ReturnsRepository;
import com.cpe.backend.Returns.entity.Status;
import com.cpe.backend.Returns.repository.StatusRepository;
import com.cpe.backend.borrow.entity.Borrow;
import com.cpe.backend.borrow.repository.BorrowRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class ReturnsTest {
    private Validator validator;

    @Autowired
    private ReturnsRepository returnsRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private MembersRepository membersRepository;
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private StatusRepository statusRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6002671_testInsertReturnOK() {
        // สร้าง object Returns
        Returns returns = new Returns();
        // กำหนด Pattern ของวัน
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // ใส่เวลา เป็น string ใน ให้ตรงกับ PattrenFormat ด้านบน
        LocalDate dataDate = LocalDate.parse((String) "2020-01-01", dateFormat);
        // ใส่ค่าที่เรากำหนดไว้
        returns.setTimeReturn(dataDate);
        // บันทึกค่าวันที่กำหนด ใน Repository
        returns = returnsRepository.saveAndFlush(returns);
        // ดึงค่าที่บันทึกมา
        Optional<Returns> found = returnsRepository.findById(returns.getReturn_id());
        // นำค่าที่ดึงมา เทียบกับค่าที่ส่งไป ว่าเหมือนกันไหม
        assertEquals(dataDate, found.get().getTimeReturn());

    }

    @Test
    void b6002671_testTimeReturnmustbaDateintehPastorPresent() throws ParseException {
        // สร้าง object Returns
        Returns returns = new Returns();
        // กำหนด Pattern ของวัน
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // ใส่ค่าที่เรากำหนดไว้
        LocalDate dataDate = LocalDate.parse((String) "2023-08-12", dateFormat);
        // ใส่ค่าที่เรากำหนดไว้
        returns.setTimeReturn(dataDate);

        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Returns>> result = validator.validate(returns);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Returns> v = result.iterator().next();
        assertEquals("must be a date in the past or in the present", v.getMessage());
        assertEquals("timeReturn", v.getPropertyPath().toString());
    }

    @Test
    void b6002671_testTimeReturnMustNotBeNull() {
        // สร้าง object Returns
        Returns returns = new Returns();
        // ใส่ค่า null 
        returns.setTimeReturn(null);
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Returns>> result = validator.validate(returns);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Returns> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("timeReturn", v.getPropertyPath().toString());
    }

}