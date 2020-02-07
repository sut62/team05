package com.cpe.backend;

import com.cpe.backend.Employee.entity.Employee;
import com.cpe.backend.Employee.repository.EmployeeRepository;
import com.cpe.backend.Members.entity.Members;
import com.cpe.backend.Members.repository.MembersRepository;
import com.cpe.backend.Members.entity.Gender;
import com.cpe.backend.Members.repository.GenderRepository;
import com.cpe.backend.Members.entity.Nametype;
import com.cpe.backend.Members.repository.NametypeRepository;
import com.cpe.backend.Returns.entity.Returns;
import com.cpe.backend.Returns.repository.ReturnsRepository;
import com.cpe.backend.Returns.entity.Status;
import com.cpe.backend.Returns.repository.StatusRepository;
import com.cpe.backend.borrow.entity.Borrow;
import com.cpe.backend.borrow.repository.BorrowRepository;
import com.cpe.backend.Sportequipment.entity.Category;
import com.cpe.backend.Sportequipment.repository.CategoryRepository;
import com.cpe.backend.Sportequipment.entity.Sportequipment;
import com.cpe.backend.Sportequipment.repository.SportequipmentRepository;
import com.cpe.backend.Sportequipment.entity.Sporttype;
import com.cpe.backend.Sportequipment.repository.SporttypeRepository;

import com.cpe.backend.Members.entity.Province;
import com.cpe.backend.Members.repository.ProvinceRepository;
import com.cpe.backend.Employee.entity.Phonetype;
import com.cpe.backend.Employee.repository.PhonetypeRepository;
import com.cpe.backend.Employee.entity.Position;
import com.cpe.backend.Employee.repository.PositionRepository;

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
import java.time.Instant;
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
    @Autowired
    private NametypeRepository nametypeRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private PhonetypeRepository phonetypeRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SportequipmentRepository sportequipmentRepository;
    @Autowired
    private SporttypeRepository sporttypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    

    @Test
    void b6002671_testInsertReturnOK() {
        // สร้าง object
        Status status = new Status();
        // กำหนดข้อมูล
        status.setStatuss("ปรกติ");
        // บันทึกค่าวันที่กำหนด ใน Repository
        status = statusRepository.saveAndFlush(status);

        // ***************************************************** */
        Phonetype phonetype = new Phonetype();
        phonetype.setPhonetype("พนักงานประจําเคาน์เตอร์");
        phonetype = phonetypeRepository.saveAndFlush(phonetype);

        Position position = new Position();
        position.setPosition("พนักงานประจําเคาน์เตอร์");
        position = positionRepository.saveAndFlush(position);

        Province province1 = new Province();
        province1.setProvince("นครสวรรค์");
        province1 = provinceRepository.saveAndFlush(province1);

        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020 - 02 - 05);
        employee.setTimeRegis(date);
        employee.setName("สมชาย นามสมมุติ");
        employee.setEmail("somsom@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province1);
        employee = employeeRepository.saveAndFlush(employee);

        // ***************************************************** */
        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);

        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);

        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date1 = new java.sql.Date(2020 - 02 - 05);
        members.setDate(date1);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setNametype(nametype);
        members.setProvince(province);
        members.setGender(gender);
        members = membersRepository.saveAndFlush(members);

        // ***************************************************** */
        Category category = new Category();
        category.setCategory_name("กีฬากลางแจ้ง");
        category = categoryRepository.saveAndFlush(category);

        Sporttype sporttype = new Sporttype();
        sporttype.setSport_type("กีฬากลางแจ้ง");
        sporttype = sporttypeRepository.saveAndFlush(sporttype);

        Sportequipment sportequipment = new Sportequipment();
        sportequipment.setBrand("Adidas");
        sportequipment.setPrice("590 บาท");
        java.sql.Date date2 = new java.sql.Date(2020 - 02 - 05);
        sportequipment.setDate(date2);
        sportequipment.setSe_name("ลูกวอลเลย์บอล");
        sportequipment.setCategory(category);
        sportequipment.setEmployee(employee);
        sportequipment.setSporttype(sporttype);

        sportequipment = sportequipmentRepository.saveAndFlush(sportequipment);

        Borrow newBorrow = new Borrow();
        DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataDate1 = LocalDate.parse((String) "2020-01-01", dateFormat1);
        newBorrow.setBorrow(dataDate1);
        newBorrow.setNote("ยืมไปเล่นในโรงยิม");
        newBorrow.setMembers(members);
        newBorrow.setEmployee(employee);
        newBorrow.setCategory(category);
        newBorrow.setSportequipment(sportequipment);
        newBorrow = borrowRepository.saveAndFlush(newBorrow);

        // ***************************************************** */
        Returns returns = new Returns();
        LocalDate dataDate = LocalDate.now();
        returns.setTimeReturn(dataDate);
        returns.setStatus(status);
        returns.setEmployee(employee);
        returns.setMember(members);
        returns.setBorrow(newBorrow);

        returns = returnsRepository.saveAndFlush(returns);
        // ดึงค่าที่บันทึกมา
        Optional<Returns> found = returnsRepository.findById(returns.getReturn_id());
        // นำค่าที่ดึงมา เทียบกับค่าที่ส่งไป ว่าเหมือนกันไหม
        assertEquals(dataDate, found.get().getTimeReturn());
        assertEquals(status, found.get().getStatus());
        assertEquals(employee, found.get().getEmployee());
        assertEquals(members, found.get().getMember());
        assertEquals(newBorrow, found.get().getBorrow());
    }

    @Test
    void b6002671_testTimeReturnmustBeaDateInThePastorPresent() throws ParseException {
        // สร้าง object
        Status status = new Status();
        // กำหนดข้อมูล
        status.setStatuss("ปรกติ");
        // บันทึกค่าวันที่กำหนด ใน Repository
        status = statusRepository.saveAndFlush(status);

        // ***************************************************** */
        Phonetype phonetype = new Phonetype();
        phonetype.setPhonetype("พนักงานประจําเคาน์เตอร์");
        phonetype = phonetypeRepository.saveAndFlush(phonetype);

        Position position = new Position();
        position.setPosition("พนักงานประจําเคาน์เตอร์");
        position = positionRepository.saveAndFlush(position);

        Province province1 = new Province();
        province1.setProvince("นครสวรรค์");
        province1 = provinceRepository.saveAndFlush(province1);

        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020 - 02 - 05);
        employee.setTimeRegis(date);
        employee.setName("สมชาย นามสมมุติ");
        employee.setEmail("somsom@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province1);
        employee = employeeRepository.saveAndFlush(employee);

        // ***************************************************** */
        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);

        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);

        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date1 = new java.sql.Date(2020 - 02 - 05);
        members.setDate(date1);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setNametype(nametype);
        members.setProvince(province);
        members.setGender(gender);
        members = membersRepository.saveAndFlush(members);

        // ***************************************************** */
        Category category = new Category();
        category.setCategory_name("กีฬากลางแจ้ง");
        category = categoryRepository.saveAndFlush(category);

        Sporttype sporttype = new Sporttype();
        sporttype.setSport_type("กีฬากลางแจ้ง");
        sporttype = sporttypeRepository.saveAndFlush(sporttype);

        Sportequipment sportequipment = new Sportequipment();
        sportequipment.setBrand("Adidas");
        sportequipment.setPrice("590 บาท");
        java.sql.Date date2 = new java.sql.Date(2020 - 02 - 05);
        sportequipment.setDate(date2);
        sportequipment.setSe_name("ลูกวอลเลย์บอล");
        sportequipment.setCategory(category);
        sportequipment.setEmployee(employee);
        sportequipment.setSporttype(sporttype);

        sportequipment = sportequipmentRepository.saveAndFlush(sportequipment);

        Borrow newBorrow = new Borrow();
        DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataDate1 = LocalDate.parse((String) "2020-01-01", dateFormat1);
        newBorrow.setBorrow(dataDate1);
        newBorrow.setNote("ยืมไปเล่นในโรงยิม");
        newBorrow.setMembers(members);
        newBorrow.setEmployee(employee);
        newBorrow.setCategory(category);
        newBorrow.setSportequipment(sportequipment);
        newBorrow = borrowRepository.saveAndFlush(newBorrow);

        Returns returns = new Returns();
        // กำหนด Pattern ของวัน
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // ใส่ค่าที่เรากำหนด
        LocalDate dataDate = LocalDate.parse((String) "2023-08-12", dateFormat);

        // ใส่ค่าที่เรากำหนดไว้
        returns.setTimeReturn(dataDate);
        returns.setStatus(status);
        returns.setEmployee(employee);
        returns.setMember(members);
        returns.setBorrow(newBorrow);

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
    void b6002671_testTimeReturnMustBeaDateInThePresentorInTheFuture()throws ParseException {
        // สร้าง object
        Status status = new Status();
        // กำหนดข้อมูล
        status.setStatuss("ปรกติ");
        // บันทึกค่าวันที่กำหนด ใน Repository
        status = statusRepository.saveAndFlush(status);

        // ***************************************************** */
        Phonetype phonetype = new Phonetype();
        phonetype.setPhonetype("พนักงานประจําเคาน์เตอร์");
        phonetype = phonetypeRepository.saveAndFlush(phonetype);

        Position position = new Position();
        position.setPosition("พนักงานประจําเคาน์เตอร์");
        position = positionRepository.saveAndFlush(position);

        Province province1 = new Province();
        province1.setProvince("นครสวรรค์");
        province1 = provinceRepository.saveAndFlush(province1);

        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020 - 02 - 05);
        employee.setTimeRegis(date);
        employee.setName("สมชาย นามสมมุติ");
        employee.setEmail("somsom@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province1);
        employee = employeeRepository.saveAndFlush(employee);

        // ***************************************************** */
        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);

        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);

        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date1 = new java.sql.Date(2020 - 02 - 05);
        members.setDate(date1);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setNametype(nametype);
        members.setProvince(province);
        members.setGender(gender);
        members = membersRepository.saveAndFlush(members);

        // ***************************************************** */
        Category category = new Category();
        category.setCategory_name("กีฬากลางแจ้ง");
        category = categoryRepository.saveAndFlush(category);

        Sporttype sporttype = new Sporttype();
        sporttype.setSport_type("กีฬากลางแจ้ง");
        sporttype = sporttypeRepository.saveAndFlush(sporttype);

        Sportequipment sportequipment = new Sportequipment();
        sportequipment.setBrand("Adidas");
        sportequipment.setPrice("590 บาท");
        java.sql.Date date2 = new java.sql.Date(2020 - 02 - 05);
        sportequipment.setDate(date2);
        sportequipment.setSe_name("ลูกวอลเลย์บอล");
        sportequipment.setCategory(category);
        sportequipment.setEmployee(employee);
        sportequipment.setSporttype(sporttype);

        sportequipment = sportequipmentRepository.saveAndFlush(sportequipment);

        Borrow newBorrow = new Borrow();
        DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataDate1 = LocalDate.parse((String) "2020-01-01", dateFormat1);
        newBorrow.setBorrow(dataDate1);
        newBorrow.setNote("ยืมไปเล่นในโรงยิม");
        newBorrow.setMembers(members);
        newBorrow.setEmployee(employee);
        newBorrow.setCategory(category);
        newBorrow.setSportequipment(sportequipment);
        newBorrow = borrowRepository.saveAndFlush(newBorrow);


        Returns returns = new Returns();
        // กำหนด Pattern ของวัน
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // ใส่ค่าที่เรากำหนด
        LocalDate dataDate = LocalDate.parse((String) "2020-01-01", dateFormat);

        // ใส่ค่าที่เรากำหนดไว้
        returns.setTimeReturn(dataDate);
        returns.setStatus(status);
        returns.setEmployee(employee);
        returns.setMember(members);
        returns.setBorrow(newBorrow);

        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Returns>> result = validator.validate(returns);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Returns> v = result.iterator().next();
        assertEquals("must be a date in the present or in the future", v.getMessage());
        assertEquals("timeReturn", v.getPropertyPath().toString());
    }
    @Test
    void b6002671_testTimeReturnMustNotBeNull() {
        // สร้าง object
        Status status = new Status();
        // กำหนดข้อมูล
        status.setStatuss("ปรกติ");
        // บันทึกค่าวันที่กำหนด ใน Repository
        status = statusRepository.saveAndFlush(status);

        // ***************************************************** */
        Phonetype phonetype = new Phonetype();
        phonetype.setPhonetype("พนักงานประจําเคาน์เตอร์");
        phonetype = phonetypeRepository.saveAndFlush(phonetype);

        Position position = new Position();
        position.setPosition("พนักงานประจําเคาน์เตอร์");
        position = positionRepository.saveAndFlush(position);

        Province province1 = new Province();
        province1.setProvince("นครสวรรค์");
        province1 = provinceRepository.saveAndFlush(province1);

        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020 - 02 - 05);
        employee.setTimeRegis(date);
        employee.setName("สมชาย นามสมมุติ");
        employee.setEmail("somsom@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province1);
        employee = employeeRepository.saveAndFlush(employee);

        // ***************************************************** */
        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);

        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);

        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date1 = new java.sql.Date(2020 - 02 - 05);
        members.setDate(date1);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setNametype(nametype);
        members.setProvince(province);
        members.setGender(gender);
        members = membersRepository.saveAndFlush(members);

        // ***************************************************** */
        Category category = new Category();
        category.setCategory_name("กีฬากลางแจ้ง");
        category = categoryRepository.saveAndFlush(category);

        Sporttype sporttype = new Sporttype();
        sporttype.setSport_type("กีฬากลางแจ้ง");
        sporttype = sporttypeRepository.saveAndFlush(sporttype);

        Sportequipment sportequipment = new Sportequipment();
        sportequipment.setBrand("Adidas");
        sportequipment.setPrice("590 บาท");
        java.sql.Date date2 = new java.sql.Date(2020 - 02 - 05);
        sportequipment.setDate(date2);
        sportequipment.setSe_name("ลูกวอลเลย์บอล");
        sportequipment.setCategory(category);
        sportequipment.setEmployee(employee);
        sportequipment.setSporttype(sporttype);

        sportequipment = sportequipmentRepository.saveAndFlush(sportequipment);

        Borrow newBorrow = new Borrow();
        DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataDate1 = LocalDate.parse((String) "2020-01-01", dateFormat1);
        newBorrow.setBorrow(dataDate1);
        newBorrow.setNote("ยืมไปเล่นในโรงยิม");
        newBorrow.setMembers(members);
        newBorrow.setEmployee(employee);
        newBorrow.setCategory(category);
        newBorrow.setSportequipment(sportequipment);
        newBorrow = borrowRepository.saveAndFlush(newBorrow);

        Returns returns = new Returns();
        // ใส่ค่า null
        returns.setTimeReturn(null);
        returns.setStatus(status);
        returns.setEmployee(employee);
        returns.setMember(members);
        returns.setBorrow(newBorrow);
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Returns>> result = validator.validate(returns);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Returns> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("timeReturn", v.getPropertyPath().toString());
    }

    @Test
    void b6002671_testStatusMustNotBeNull() {
        // สร้าง object
        Status status = new Status();
        // กำหนดข้อมูล
        status.setStatuss("ปรกติ");
        // บันทึกค่าวันที่กำหนด ใน Repository
        status = statusRepository.saveAndFlush(status);

        // ***************************************************** */
        Phonetype phonetype = new Phonetype();
        phonetype.setPhonetype("พนักงานประจําเคาน์เตอร์");
        phonetype = phonetypeRepository.saveAndFlush(phonetype);

        Position position = new Position();
        position.setPosition("พนักงานประจําเคาน์เตอร์");
        position = positionRepository.saveAndFlush(position);

        Province province1 = new Province();
        province1.setProvince("นครสวรรค์");
        province1 = provinceRepository.saveAndFlush(province1);

        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020 - 02 - 05);
        employee.setTimeRegis(date);
        employee.setName("สมชาย นามสมมุติ");
        employee.setEmail("somsom@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province1);
        employee = employeeRepository.saveAndFlush(employee);

        // ***************************************************** */
        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);

        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);

        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date1 = new java.sql.Date(2020 - 02 - 05);
        members.setDate(date1);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setNametype(nametype);
        members.setProvince(province);
        members.setGender(gender);
        members = membersRepository.saveAndFlush(members);

        // ***************************************************** */
        Category category = new Category();
        category.setCategory_name("กีฬากลางแจ้ง");
        category = categoryRepository.saveAndFlush(category);

        Sporttype sporttype = new Sporttype();
        sporttype.setSport_type("กีฬากลางแจ้ง");
        sporttype = sporttypeRepository.saveAndFlush(sporttype);

        Sportequipment sportequipment = new Sportequipment();
        sportequipment.setBrand("Adidas");
        sportequipment.setPrice("590 บาท");
        java.sql.Date date2 = new java.sql.Date(2020 - 02 - 05);
        sportequipment.setDate(date2);
        sportequipment.setSe_name("ลูกวอลเลย์บอล");
        sportequipment.setCategory(category);
        sportequipment.setEmployee(employee);
        sportequipment.setSporttype(sporttype);

        sportequipment = sportequipmentRepository.saveAndFlush(sportequipment);

        Borrow newBorrow = new Borrow();
        DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataDate1 = LocalDate.parse((String) "2020-01-01", dateFormat1);
        newBorrow.setBorrow(dataDate1);
        newBorrow.setNote("ยืมไปเล่นในโรงยิม");
        newBorrow.setMembers(members);
        newBorrow.setEmployee(employee);
        newBorrow.setCategory(category);
        newBorrow.setSportequipment(sportequipment);
        newBorrow = borrowRepository.saveAndFlush(newBorrow);

        Returns returns = new Returns();

        LocalDate dataDate = LocalDate.now();
        returns.setTimeReturn(dataDate);
        // ใส่ค่า null
        returns.setStatus(null);
        returns.setEmployee(employee);
        returns.setMember(members);
        returns.setBorrow(newBorrow);
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Returns>> result = validator.validate(returns);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Returns> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("status", v.getPropertyPath().toString());
    }

    @Test
    void b6002671_testEmployeeMustNotBeNull() {
        // สร้าง object
        Status status = new Status();
        // กำหนดข้อมูล
        status.setStatuss("ปรกติ");
        // บันทึกค่าวันที่กำหนด ใน Repository
        status = statusRepository.saveAndFlush(status);

        // ***************************************************** */
        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);

        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);

        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date1 = new java.sql.Date(2020 - 02 - 05);
        members.setDate(date1);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setNametype(nametype);
        members.setProvince(province);
        members.setGender(gender);
        members = membersRepository.saveAndFlush(members);

        // ***************************************************** */
        Phonetype phonetype = new Phonetype();
        phonetype.setPhonetype("พนักงานประจําเคาน์เตอร์");
        phonetype = phonetypeRepository.saveAndFlush(phonetype);

        Position position = new Position();
        position.setPosition("พนักงานประจําเคาน์เตอร์");
        position = positionRepository.saveAndFlush(position);

        Province province1 = new Province();
        province1.setProvince("นครสวรรค์");
        province1 = provinceRepository.saveAndFlush(province1);

        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020 - 02 - 05);
        employee.setTimeRegis(date);
        employee.setName("สมชาย นามสมมุติ");
        employee.setEmail("somsom@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province1);
        employee = employeeRepository.saveAndFlush(employee);

        // ***************************************************** */
        Category category = new Category();
        category.setCategory_name("กีฬากลางแจ้ง");
        category = categoryRepository.saveAndFlush(category);

        Sporttype sporttype = new Sporttype();
        sporttype.setSport_type("กีฬากลางแจ้ง");
        sporttype = sporttypeRepository.saveAndFlush(sporttype);

        Sportequipment sportequipment = new Sportequipment();
        sportequipment.setBrand("Adidas");
        sportequipment.setPrice("590 บาท");
        java.sql.Date date2 = new java.sql.Date(2020 - 02 - 05);
        sportequipment.setDate(date2);
        sportequipment.setSe_name("ลูกวอลเลย์บอล");
        sportequipment.setCategory(category);
        sportequipment.setEmployee(employee);
        sportequipment.setSporttype(sporttype);

        sportequipment = sportequipmentRepository.saveAndFlush(sportequipment);

        Borrow newBorrow = new Borrow();
        DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataDate1 = LocalDate.parse((String) "2020-01-01", dateFormat1);
        newBorrow.setBorrow(dataDate1);
        newBorrow.setNote("ยืมไปเล่นในโรงยิม");
        newBorrow.setMembers(members);
        newBorrow.setEmployee(employee);
        newBorrow.setCategory(category);
        newBorrow.setSportequipment(sportequipment);
        newBorrow = borrowRepository.saveAndFlush(newBorrow);

        Returns returns = new Returns();

        LocalDate dataDate = LocalDate.now();
        returns.setTimeReturn(dataDate);
        returns.setStatus(status);
        // ใส่ค่า null
        returns.setEmployee(null);
        returns.setMember(members);
        returns.setBorrow(newBorrow);
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Returns>> result = validator.validate(returns);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Returns> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("employee", v.getPropertyPath().toString());
    }

    @Test
    void b6002671_testMemberMustNotBeNull() {
        // สร้าง object
        Status status = new Status();
        // กำหนดข้อมูล
        status.setStatuss("ปรกติ");
        // บันทึกค่าวันที่กำหนด ใน Repository
        status = statusRepository.saveAndFlush(status);

        // ***************************************************** */
        Phonetype phonetype = new Phonetype();
        phonetype.setPhonetype("พนักงานประจําเคาน์เตอร์");
        phonetype = phonetypeRepository.saveAndFlush(phonetype);

        Position position = new Position();
        position.setPosition("พนักงานประจําเคาน์เตอร์");
        position = positionRepository.saveAndFlush(position);

        Province province1 = new Province();
        province1.setProvince("นครสวรรค์");
        province1 = provinceRepository.saveAndFlush(province1);

        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020 - 02 - 05);
        employee.setTimeRegis(date);
        employee.setName("สมชาย นามสมมุติ");
        employee.setEmail("somsom@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province1);
        employee = employeeRepository.saveAndFlush(employee);

        // ***************************************************** */
        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);

        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);

        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date1 = new java.sql.Date(2020 - 02 - 05);
        members.setDate(date1);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setNametype(nametype);
        members.setProvince(province);
        members.setGender(gender);
        members = membersRepository.saveAndFlush(members);

        // ***************************************************** */
        Category category = new Category();
        category.setCategory_name("กีฬากลางแจ้ง");
        category = categoryRepository.saveAndFlush(category);

        Sporttype sporttype = new Sporttype();
        sporttype.setSport_type("กีฬากลางแจ้ง");
        sporttype = sporttypeRepository.saveAndFlush(sporttype);

        Sportequipment sportequipment = new Sportequipment();
        sportequipment.setBrand("Adidas");
        sportequipment.setPrice("590 บาท");
        java.sql.Date date2 = new java.sql.Date(2020 - 02 - 05);
        sportequipment.setDate(date2);
        sportequipment.setSe_name("ลูกวอลเลย์บอล");
        sportequipment.setCategory(category);
        sportequipment.setEmployee(employee);
        sportequipment.setSporttype(sporttype);

        sportequipment = sportequipmentRepository.saveAndFlush(sportequipment);

        Borrow newBorrow = new Borrow();
        DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataDate1 = LocalDate.parse((String) "2020-01-01", dateFormat1);
        newBorrow.setBorrow(dataDate1);
        newBorrow.setNote("ยืมไปเล่นในโรงยิม");
        newBorrow.setMembers(members);
        newBorrow.setEmployee(employee);
        newBorrow.setCategory(category);
        newBorrow.setSportequipment(sportequipment);
        newBorrow = borrowRepository.saveAndFlush(newBorrow);

        Returns returns = new Returns();
        LocalDate dataDate = LocalDate.now();
        returns.setTimeReturn(dataDate);
        returns.setStatus(status);
        returns.setEmployee(employee);
        // ใส่ค่า null
        returns.setMember(null);
        returns.setBorrow(newBorrow);
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Returns>> result = validator.validate(returns);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Returns> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("member", v.getPropertyPath().toString());
    }

    @Test
    void b6002671_testBorrowMustNotBeNull() {
        // สร้าง object
        Status status = new Status();
        // กำหนดข้อมูล
        status.setStatuss("ปรกติ");
        // บันทึกค่าวันที่กำหนด ใน Repository
        status = statusRepository.saveAndFlush(status);

        // ***************************************************** */
        Phonetype phonetype = new Phonetype();
        phonetype.setPhonetype("พนักงานประจําเคาน์เตอร์");
        phonetype = phonetypeRepository.saveAndFlush(phonetype);

        Position position = new Position();
        position.setPosition("พนักงานประจําเคาน์เตอร์");
        position = positionRepository.saveAndFlush(position);

        Province province1 = new Province();
        province1.setProvince("นครสวรรค์");
        province1 = provinceRepository.saveAndFlush(province1);

        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020 - 02 - 05);
        employee.setTimeRegis(date);
        employee.setName("สมชาย นามสมมุติ");
        employee.setEmail("somsom@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province1);
        employee = employeeRepository.saveAndFlush(employee);

        // ***************************************************** */
        Nametype nametype = new Nametype();
        nametype.setNametype("นาย");
        nametype = nametypeRepository.saveAndFlush(nametype);

        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);

        Gender gender = new Gender();
        gender.setGender("ชาย");
        gender = genderRepository.saveAndFlush(gender);

        Members members = new Members();
        members.setUsername("Arm6827");
        members.setName("กิตติพันธ์  เฟื่องคร");
        java.sql.Date date1 = new java.sql.Date(2020 - 02 - 05);
        members.setDate(date1);
        members.setAddress("195 ม.1 ต.แม่วงก์ อ.แม่วงก์");
        members.setEmail("arm68276728@gmail.com");
        members.setPhonenumber("0902408126");
        members.setNametype(nametype);
        members.setProvince(province);
        members.setGender(gender);
        members = membersRepository.saveAndFlush(members);

        Returns returns = new Returns();
        LocalDate dataDate = LocalDate.now();
        returns.setTimeReturn(dataDate);
        returns.setStatus(status);
        returns.setEmployee(employee);
        returns.setMember(members);
        // ใส่ค่า null
        returns.setBorrow(null);
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Returns>> result = validator.validate(returns);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Returns> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("borrow", v.getPropertyPath().toString());
    }
}