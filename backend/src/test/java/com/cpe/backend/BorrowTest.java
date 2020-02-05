package com.cpe.backend;

import com.cpe.backend.borrow.repository.BorrowRepository;
import com.cpe.backend.borrow.entity.Borrow;
import com.cpe.backend.Employee.entity.Employee;
import com.cpe.backend.Employee.entity.Phonetype;
import com.cpe.backend.Employee.entity.Position;
import com.cpe.backend.Employee.repository.EmployeeRepository;
import com.cpe.backend.Employee.repository.PhonetypeRepository;
import com.cpe.backend.Employee.repository.PositionRepository;
import com.cpe.backend.Members.entity.Gender;
import com.cpe.backend.Members.entity.Members;
import com.cpe.backend.Members.entity.Nametype;
import com.cpe.backend.Members.entity.Province;
import com.cpe.backend.Members.repository.GenderRepository;
import com.cpe.backend.Members.repository.MembersRepository;
import com.cpe.backend.Members.repository.NametypeRepository;
import com.cpe.backend.Members.repository.ProvinceRepository;
import com.cpe.backend.Sportequipment.entity.Category;
import com.cpe.backend.Sportequipment.repository.CategoryRepository;
import com.cpe.backend.Sportequipment.entity.Sportequipment;
import com.cpe.backend.Sportequipment.entity.Sporttype;
import com.cpe.backend.Sportequipment.repository.SportequipmentRepository;
import com.cpe.backend.Sportequipment.repository.SporttypeRepository;

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
    @Autowired
    private PhonetypeRepository phonetypeRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private NametypeRepository nametypeRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private SporttypeRepository sporttypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }

    @Test
    void b6002664_testInsertOK() {
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
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataDate = LocalDate.parse((String) "2020-01-01", dateFormat);
        newBorrow.setBorrow(dataDate);
        newBorrow.setNote("ยืมไปเล่นในโรงยิม");
        newBorrow.setMembers(members);
        newBorrow.setEmployee(employee);
        newBorrow.setCategory(category);
        newBorrow.setSportequipment(sportequipment);
        newBorrow = borrowRepository.saveAndFlush(newBorrow);

        // ดึงค่าที่บันทึกมา
        Optional<Borrow> found = borrowRepository.findById(newBorrow.getBorrow_id());
        // นำค่าที่ดึงมา เทียบกับค่าที่ส่งไป ว่าเหมือนกันไหม
        assertEquals(dataDate, found.get().getBorrow_date());
        assertEquals(employee, found.get().getEmployee());
        assertEquals(members, found.get().getMembers());
        assertEquals(sportequipment, found.get().getSportequipment());

    }

    @Test
    void b6002664_testBorrow_datemustbeDateinthePast_or_Present() throws ParseException {

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
        // กำหนด Pattern ของวัน
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // ใส่ค่าที่เรากำหนดไว้
        LocalDate dataDate = LocalDate.parse((String) "2023-08-12", dateFormat);

        // ใส่ค่าที่เรากำหนดไว้
        newBorrow.setBorrow(dataDate);
        newBorrow.setNote("ยืมไปเล่นในโรงยิม");
        newBorrow.setMembers(members);
        newBorrow.setEmployee(employee);
        newBorrow.setCategory(category);
        newBorrow.setSportequipment(sportequipment);

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
    void b6002664_testBorrow_datemustNotbeNull() throws ParseException {

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
        // กำหนด Pattern ของวัน
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // ใส่ค่าที่เรากำหนดไว้
        LocalDate dataDate = LocalDate.parse((String) "2020-01-01", dateFormat);

        // ใส่ค่าที่เรากำหนดไว้
        newBorrow.setBorrow(null);
        newBorrow.setNote("ยืมไปเล่นในโรงยิม");
        newBorrow.setMembers(members);
        newBorrow.setEmployee(employee);
        newBorrow.setCategory(category);
        newBorrow.setSportequipment(sportequipment);

        Set<ConstraintViolation<Borrow>> result = validator.validate(newBorrow);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Borrow> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("borrow_date", v.getPropertyPath().toString());

    }

    @Test
    void b6002664_testNoteBeGreaterEqual20() throws ParseException {

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
        // กำหนด Pattern ของวัน
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // ใส่ค่าที่เรากำหนดไว้
        LocalDate dataDate = LocalDate.parse((String) "2020-01-01", dateFormat);

        // ใส่ค่าที่เรากำหนดไว้
        newBorrow.setBorrow(dataDate);
        newBorrow.setNote("ยืมไปเล่นในโรงยิมกับเพื่อนๆ");
        newBorrow.setMembers(members);
        newBorrow.setEmployee(employee);
        newBorrow.setCategory(category);
        newBorrow.setSportequipment(sportequipment);

        Set<ConstraintViolation<Borrow>> result = validator.validate(newBorrow);
        assertEquals(1, result.size());
        assertEquals("size must be between 5 and 20", result.iterator().next().getMessage());
        assertEquals("note", result.iterator().next().getPropertyPath().toString());

    }

    @Test
    void b6002664_testMembersMustNotBeNull() {
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
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataDate = LocalDate.parse((String) "2020-01-01", dateFormat);
        newBorrow.setBorrow(dataDate);
        newBorrow.setNote("ยืมไปเล่นในโรงยิม");
        newBorrow.setMembers(null);
        newBorrow.setEmployee(employee);
        newBorrow.setCategory(category);
        newBorrow.setSportequipment(sportequipment);

        Set<ConstraintViolation<Borrow>> result = validator.validate(newBorrow);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Borrow> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("members", v.getPropertyPath().toString());
    }

    @Test
    void b6002664_testEmployeeMustNotBeNull() {
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
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataDate = LocalDate.parse((String) "2020-01-01", dateFormat);
        newBorrow.setBorrow(dataDate);
        newBorrow.setNote("ยืมไปเล่นในโรงยิม");
        newBorrow.setMembers(members);
        newBorrow.setEmployee(null);
        newBorrow.setCategory(category);
        newBorrow.setSportequipment(sportequipment);

        Set<ConstraintViolation<Borrow>> result = validator.validate(newBorrow);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Borrow> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("employee", v.getPropertyPath().toString());
    } @Test
    void b6002664_testCategoryMustNotBeNull() {
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
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataDate = LocalDate.parse((String) "2020-01-01", dateFormat);
        newBorrow.setBorrow(dataDate);
        newBorrow.setNote("ยืมไปเล่นในโรงยิม");
        newBorrow.setMembers(members);
        newBorrow.setEmployee(employee);
        newBorrow.setCategory(null);
        newBorrow.setSportequipment(sportequipment);

        Set<ConstraintViolation<Borrow>> result = validator.validate(newBorrow);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Borrow> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("category", v.getPropertyPath().toString());
    } @Test
    void b6002664_testSportequipmentMustNotBeNull() {
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
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataDate = LocalDate.parse((String) "2020-01-01", dateFormat);
        newBorrow.setBorrow(dataDate);
        newBorrow.setNote("ยืมไปเล่นในโรงยิม");
        newBorrow.setMembers(members);
        newBorrow.setEmployee(employee);
        newBorrow.setCategory(category);
        newBorrow.setSportequipment(null);

        Set<ConstraintViolation<Borrow>> result = validator.validate(newBorrow);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Borrow> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("sportequipment", v.getPropertyPath().toString());
    }



}