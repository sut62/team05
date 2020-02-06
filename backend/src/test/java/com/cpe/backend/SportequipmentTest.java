package com.cpe.backend;

import com.cpe.backend.Employee.entity.Employee;
import com.cpe.backend.Employee.entity.Phonetype;
import com.cpe.backend.Employee.entity.Position;
import com.cpe.backend.Employee.repository.EmployeeRepository;
import com.cpe.backend.Employee.repository.PhonetypeRepository;
import com.cpe.backend.Employee.repository.PositionRepository;
import com.cpe.backend.Members.entity.Province;
import com.cpe.backend.Members.repository.ProvinceRepository;
import com.cpe.backend.Sportequipment.entity.Category;
import com.cpe.backend.Sportequipment.entity.Sportequipment;
import com.cpe.backend.Sportequipment.entity.Sporttype;
import com.cpe.backend.Sportequipment.repository.CategoryRepository;
import com.cpe.backend.Sportequipment.repository.SportequipmentRepository;
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
public class SportequipmentTest {
    private Validator validator;

    @Autowired
    private SportequipmentRepository sportequipmentRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SporttypeRepository sporttypeRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private PhonetypeRepository phonetypeRepository;
    @Autowired
    private PositionRepository positionRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
   
//========================================================================================================================
//brand
@Test
void b6020163_testSportequipmentInsertFullDataOK() {
    Category category = new Category();
    category.setCategory_name("กีฬากลางแจ้ง");
    category = categoryRepository.saveAndFlush(category);


    Sporttype sporttype = new Sporttype();
    sporttype.setSport_type("กีฬากลางแจ้ง");
    sporttype = sporttypeRepository.saveAndFlush(sporttype);
    
    Phonetype phonetype = new Phonetype();
    phonetype.setPhonetype("มือถือส่วนตัว");
    phonetype = phonetypeRepository.saveAndFlush(phonetype);
    Position position = new Position();
    position.setPosition("พนักงานประจําเคาน์เตอร์");
    position = positionRepository.saveAndFlush(position);
    Province province = new Province();
    province.setProvince("นครสวรรค์");
    province = provinceRepository.saveAndFlush(province);
    Employee employee = new Employee();
    java.sql.Date date = new java.sql.Date(2020-02-05);
    employee.setTimeRegis(date);
    employee.setName("สมชาย นามสมมุติ");
    employee.setEmail("somsom@gmail.com");
    employee.setPassword("12345678");
    employee.setPhonenumber("0123456789");
    employee.setPhonetype(phonetype);
    employee.setPosition(position);
    employee.setProvince(province);
    employee = employeeRepository.saveAndFlush(employee); 
    
    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand("Adidas");
    sportequipment.setPrice("590 บาท");
    sportequipment.setDate(date);
    sportequipment.setSe_name("ลูกวอลเลย์บอล");
    sportequipment.setCategory(category);
    sportequipment.setSporttype(sporttype);
    sportequipment.setEmployee(employee);
    sportequipment = sportequipmentRepository.saveAndFlush(sportequipment);

    Optional<Sportequipment> found = sportequipmentRepository.findById(sportequipment.getId());
    assertEquals(sportequipment.getBrand(), found.get().getBrand());
    assertEquals(sportequipment.getPrice(), found.get().getPrice());
    assertEquals(sportequipment.getDate(), found.get().getDate());
    assertEquals(sportequipment.getSe_name(), found.get().getSe_name());
    assertEquals(category, found.get().getCategory());
    assertEquals(sporttype, found.get().getSporttype());
    assertEquals(employee, found.get().getEmployee());
}
@Test
void b6020163_testBrandforSportequipmentMustNotBeNull() {
    Category category = new Category();
    category.setCategory_name("กีฬากลางแจ้ง");
    category = categoryRepository.saveAndFlush(category);


    Sporttype sporttype = new Sporttype();
    sporttype.setSport_type("กีฬากลางแจ้ง");
    sporttype = sporttypeRepository.saveAndFlush(sporttype);
    
    Phonetype phonetype = new Phonetype();
    phonetype.setPhonetype("มือถือส่วนตัว");
    phonetype = phonetypeRepository.saveAndFlush(phonetype);
    Position position = new Position();
    position.setPosition("พนักงานประจําเคาน์เตอร์");
    position = positionRepository.saveAndFlush(position);
    Province province = new Province();
    province.setProvince("นครสวรรค์");
    province = provinceRepository.saveAndFlush(province);
    Employee employee = new Employee();
    java.sql.Date date = new java.sql.Date(2020-02-05);
    employee.setTimeRegis(date);
    employee.setName("สมชาย นามสมมุติ");
    employee.setEmail("somsom@gmail.com");
    employee.setPassword("12345678");
    employee.setPhonenumber("0123456789");
    employee.setPhonetype(phonetype);
    employee.setPosition(position);
    employee.setProvince(province);
    employee = employeeRepository.saveAndFlush(employee); 
    
    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand(null);
    sportequipment.setPrice("590 บาท");
    sportequipment.setDate(date);
    sportequipment.setSe_name("ลูกวอลเลย์บอล");
    sportequipment.setCategory(category);
    sportequipment.setSporttype(sporttype);
    sportequipment.setEmployee(employee);
    
    Set<ConstraintViolation<Sportequipment>> result = validator.validate(sportequipment);
    assertEquals(1, result.size());
    assertEquals("must not be null", result.iterator().next().getMessage());
    assertEquals("brand", result.iterator().next().getPropertyPath().toString());
}
@Test
void b6020163_testBrandMustBeGreaterEqual3() {
    Category category = new Category();
    category.setCategory_name("กีฬากลางแจ้ง");
    category = categoryRepository.saveAndFlush(category);


    Sporttype sporttype = new Sporttype();
    sporttype.setSport_type("กีฬากลางแจ้ง");
    sporttype = sporttypeRepository.saveAndFlush(sporttype);
    
    Phonetype phonetype = new Phonetype();
    phonetype.setPhonetype("มือถือส่วนตัว");
    phonetype = phonetypeRepository.saveAndFlush(phonetype);
    Position position = new Position();
    position.setPosition("พนักงานประจําเคาน์เตอร์");
    position = positionRepository.saveAndFlush(position);
    Province province = new Province();
    province.setProvince("นครสวรรค์");
    province = provinceRepository.saveAndFlush(province);
    Employee employee = new Employee();
    java.sql.Date date = new java.sql.Date(2020-02-05);
    employee.setTimeRegis(date);
    employee.setName("สมชาย นามสมมุติ");
    employee.setEmail("somsom@gmail.com");
    employee.setPassword("12345678");
    employee.setPhonenumber("0123456789");
    employee.setPhonetype(phonetype);
    employee.setPosition(position);
    employee.setProvince(province);
    employee = employeeRepository.saveAndFlush(employee);

    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand("Ad");
    sportequipment.setPrice("590 บาท");
    sportequipment.setDate(date);
    sportequipment.setSe_name("ลูกวอลเลย์บอล");
    sportequipment.setCategory(category);
    sportequipment.setSporttype(sporttype);
    sportequipment.setEmployee(employee);

    Set<ConstraintViolation<Sportequipment>> result = validator.validate(sportequipment);
    assertEquals(1, result.size());
    assertEquals("size must be between 3 and 15", result.iterator().next().getMessage());
    assertEquals("brand", result.iterator().next().getPropertyPath().toString());
}
@Test
void b6020163_testBrandMustBeGreaterEqual15() {
    Category category = new Category();
    category.setCategory_name("กีฬากลางแจ้ง");
    category = categoryRepository.saveAndFlush(category);

    Sporttype sporttype = new Sporttype();
    sporttype.setSport_type("กีฬากลางแจ้ง");
    sporttype = sporttypeRepository.saveAndFlush(sporttype);
    
    Phonetype phonetype = new Phonetype();
    phonetype.setPhonetype("มือถือส่วนตัว");
    phonetype = phonetypeRepository.saveAndFlush(phonetype);
    Position position = new Position();
    position.setPosition("พนักงานประจําเคาน์เตอร์");
    position = positionRepository.saveAndFlush(position);
    Province province = new Province();
    province.setProvince("นครสวรรค์");
    province = provinceRepository.saveAndFlush(province);
    Employee employee = new Employee();
    java.sql.Date date = new java.sql.Date(2020-02-05);
    employee.setTimeRegis(date);
    employee.setName("สมชาย นามสมมุติ");
    employee.setEmail("somsom@gmail.com");
    employee.setPassword("12345678");
    employee.setPhonenumber("0123456789");
    employee.setPhonetype(phonetype);
    employee.setPosition(position);
    employee.setProvince(province);
    employee = employeeRepository.saveAndFlush(employee);


    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand("AdidasAdidasAdidas");
    sportequipment.setPrice("590 บาท");
    sportequipment.setDate(date);
    sportequipment.setSe_name("ลูกวอลเลย์บอล");
    sportequipment.setCategory(category);
    sportequipment.setSporttype(sporttype);
    sportequipment.setEmployee(employee);

    Set<ConstraintViolation<Sportequipment>> result = validator.validate(sportequipment);
    assertEquals(1, result.size());
    assertEquals("size must be between 3 and 15", result.iterator().next().getMessage());
    assertEquals("brand", result.iterator().next().getPropertyPath().toString());
}
@Test
    void b6020163_testBrandNotExactlyThePattern() {
        Category category = new Category();
        category.setCategory_name("กีฬากลางแจ้ง");
        category = categoryRepository.saveAndFlush(category);
    
        Sporttype sporttype = new Sporttype();
        sporttype.setSport_type("กีฬากลางแจ้ง");
        sporttype = sporttypeRepository.saveAndFlush(sporttype);
        
        Phonetype phonetype = new Phonetype();
        phonetype.setPhonetype("มือถือส่วนตัว");
        phonetype = phonetypeRepository.saveAndFlush(phonetype);
        Position position = new Position();
        position.setPosition("พนักงานประจําเคาน์เตอร์");
        position = positionRepository.saveAndFlush(position);
        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);
        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020-02-05);
        employee.setTimeRegis(date);
        employee.setName("สมชาย นามสมมุติ");
        employee.setEmail("somsom@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province);
        employee = employeeRepository.saveAndFlush(employee);
    
    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand("33445515555459");
    sportequipment.setPrice("590 บาท");
    sportequipment.setDate(date);
    sportequipment.setSe_name("ลูกวอลเลย์บอล");
    sportequipment.setCategory(category);
    sportequipment.setSporttype(sporttype);
    sportequipment.setEmployee(employee);

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
    Category category = new Category();
    category.setCategory_name("กีฬากลางแจ้ง");
    category = categoryRepository.saveAndFlush(category);

    Sporttype sporttype = new Sporttype();
    sporttype.setSport_type("กีฬากลางแจ้ง");
    sporttype = sporttypeRepository.saveAndFlush(sporttype);
    
    Phonetype phonetype = new Phonetype();
    phonetype.setPhonetype("มือถือส่วนตัว");
    phonetype = phonetypeRepository.saveAndFlush(phonetype);
    Position position = new Position();
    position.setPosition("พนักงานประจําเคาน์เตอร์");
    position = positionRepository.saveAndFlush(position);
    Province province = new Province();
    province.setProvince("นครสวรรค์");
    province = provinceRepository.saveAndFlush(province);
    Employee employee = new Employee();
    java.sql.Date date = new java.sql.Date(2020-02-05);
    employee.setTimeRegis(date);
    employee.setName("สมชาย นามสมมุติ");
    employee.setEmail("somsom@gmail.com");
    employee.setPassword("12345678");
    employee.setPhonenumber("0123456789");
    employee.setPhonetype(phonetype);
    employee.setPosition(position);
    employee.setProvince(province);
    employee = employeeRepository.saveAndFlush(employee);

    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand("adidas");
    sportequipment.setPrice(null);
    sportequipment.setDate(date);
    sportequipment.setSe_name("ลูกวอลเลย์บอล");
    sportequipment.setCategory(category);
    sportequipment.setSporttype(sporttype);
    sportequipment.setEmployee(employee);
    
    Set<ConstraintViolation<Sportequipment>> result = validator.validate(sportequipment);
    assertEquals(1, result.size());
    assertEquals("must not be null", result.iterator().next().getMessage());
    assertEquals("price", result.iterator().next().getPropertyPath().toString());
}

//========================================================================================================================
//date
@Test
void b6020163_testDateforSportequipmentMustNotBeNull() {
    Category category = new Category();
    category.setCategory_name("กีฬากลางแจ้ง");
    category = categoryRepository.saveAndFlush(category);

    Sporttype sporttype = new Sporttype();
    sporttype.setSport_type("กีฬากลางแจ้ง");
    sporttype = sporttypeRepository.saveAndFlush(sporttype);
    
    Phonetype phonetype = new Phonetype();
    phonetype.setPhonetype("มือถือส่วนตัว");
    phonetype = phonetypeRepository.saveAndFlush(phonetype);
    Position position = new Position();
    position.setPosition("พนักงานประจําเคาน์เตอร์");
    position = positionRepository.saveAndFlush(position);
    Province province = new Province();
    province.setProvince("นครสวรรค์");
    province = provinceRepository.saveAndFlush(province);
    Employee employee = new Employee();
    java.sql.Date date = new java.sql.Date(2020-02-05);
    employee.setTimeRegis(date);
    employee.setName("สมชาย นามสมมุติ");
    employee.setEmail("somsom@gmail.com");
    employee.setPassword("12345678");
    employee.setPhonenumber("0123456789");
    employee.setPhonetype(phonetype);
    employee.setPosition(position);
    employee.setProvince(province);
    employee = employeeRepository.saveAndFlush(employee);

    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand("adidas");
    sportequipment.setPrice("590 บาท");
    sportequipment.setDate(null);
    sportequipment.setSe_name("ลูกวอลเลย์บอล");
    sportequipment.setCategory(category);
    sportequipment.setSporttype(sporttype);
    sportequipment.setEmployee(employee);
    
    Set<ConstraintViolation<Sportequipment>> result = validator.validate(sportequipment);
    assertEquals(1, result.size());
    assertEquals("must not be null", result.iterator().next().getMessage());
    assertEquals("date", result.iterator().next().getPropertyPath().toString());
}

//========================================================================================================================
//se_name
@Test
void b6020163_testSe_nameforSportequipmentMustNotBeNull() {
    Category category = new Category();
    category.setCategory_name("กีฬากลางแจ้ง");
    category = categoryRepository.saveAndFlush(category);

    Sporttype sporttype = new Sporttype();
    sporttype.setSport_type("กีฬากลางแจ้ง");
    sporttype = sporttypeRepository.saveAndFlush(sporttype);
    
    Phonetype phonetype = new Phonetype();
    phonetype.setPhonetype("มือถือส่วนตัว");
    phonetype = phonetypeRepository.saveAndFlush(phonetype);
    Position position = new Position();
    position.setPosition("พนักงานประจําเคาน์เตอร์");
    position = positionRepository.saveAndFlush(position);
    Province province = new Province();
    province.setProvince("นครสวรรค์");
    province = provinceRepository.saveAndFlush(province);
    Employee employee = new Employee();
    java.sql.Date date = new java.sql.Date(2020-02-05);
    employee.setTimeRegis(date);
    employee.setName("สมชาย นามสมมุติ");
    employee.setEmail("somsom@gmail.com");
    employee.setPassword("12345678");
    employee.setPhonenumber("0123456789");
    employee.setPhonetype(phonetype);
    employee.setPosition(position);
    employee.setProvince(province);
    employee = employeeRepository.saveAndFlush(employee);


    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand("adidas");
    sportequipment.setPrice("590 บาท");
    sportequipment.setDate(date);
    sportequipment.setSe_name(null);
    sportequipment.setCategory(category);
    sportequipment.setSporttype(sporttype);
    sportequipment.setEmployee(employee);
    
    Set<ConstraintViolation<Sportequipment>> result = validator.validate(sportequipment);
    assertEquals(1, result.size());
    assertEquals("must not be null", result.iterator().next().getMessage());
    assertEquals("se_name", result.iterator().next().getPropertyPath().toString());
}
@Test
    void b6020163_testSe_nameNotExactlyThePattern() {
        Category category = new Category();
        category.setCategory_name("กีฬากลางแจ้ง");
        category = categoryRepository.saveAndFlush(category);
    
        Sporttype sporttype = new Sporttype();
        sporttype.setSport_type("กีฬากลางแจ้ง");
        sporttype = sporttypeRepository.saveAndFlush(sporttype);
        
        Phonetype phonetype = new Phonetype();
        phonetype.setPhonetype("มือถือส่วนตัว");
        phonetype = phonetypeRepository.saveAndFlush(phonetype);
        Position position = new Position();
        position.setPosition("พนักงานประจําเคาน์เตอร์");
        position = positionRepository.saveAndFlush(position);
        Province province = new Province();
        province.setProvince("นครสวรรค์");
        province = provinceRepository.saveAndFlush(province);
        Employee employee = new Employee();
        java.sql.Date date = new java.sql.Date(2020-02-05);
        employee.setTimeRegis(date);
        employee.setName("สมชาย นามสมมุติ");
        employee.setEmail("somsom@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province);
        employee = employeeRepository.saveAndFlush(employee);

    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand("adidas");
    sportequipment.setPrice("590 บาท");
    sportequipment.setDate(date);
    sportequipment.setSe_name("1254698%%%%");
    sportequipment.setCategory(category);
    sportequipment.setSporttype(sporttype);
    sportequipment.setEmployee(employee);

        Set<ConstraintViolation<Sportequipment>> result = validator.validate(sportequipment);
        assertEquals(1, result.size());
        ConstraintViolation<Sportequipment> v = result.iterator().next();
        assertEquals("must match \"[ก-๙ a-zA-Z]*\"", v.getMessage());
        assertEquals("se_name", v.getPropertyPath().toString());
    }

    @Test
void b6020163_testEmployeeforSportequipmentMustNotBeNull() {
    Category category = new Category();
    category.setCategory_name("กีฬากลางแจ้ง");
    category = categoryRepository.saveAndFlush(category);

    Sporttype sporttype = new Sporttype();
    sporttype.setSport_type("กีฬากลางแจ้ง");
    sporttype = sporttypeRepository.saveAndFlush(sporttype);
    
    Phonetype phonetype = new Phonetype();
    phonetype.setPhonetype("มือถือส่วนตัว");
    phonetype = phonetypeRepository.saveAndFlush(phonetype);
    Position position = new Position();
    position.setPosition("พนักงานประจําเคาน์เตอร์");
    position = positionRepository.saveAndFlush(position);
    Province province = new Province();
    province.setProvince("นครสวรรค์");
    province = provinceRepository.saveAndFlush(province);
    Employee employee = new Employee();
    java.sql.Date date = new java.sql.Date(2020-02-05);
    employee.setTimeRegis(date);
    employee.setName("สมชาย นามสมมุติ");
    employee.setEmail("somsom@gmail.com");
    employee.setPassword("12345678");
    employee.setPhonenumber("0123456789");
    employee.setPhonetype(phonetype);
    employee.setPosition(position);
    employee.setProvince(province);
    employee = employeeRepository.saveAndFlush(employee);

    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand("adidas");
    sportequipment.setPrice("590");
    sportequipment.setDate(date);
    sportequipment.setSe_name("ลูกวอลเลย์บอล");
    sportequipment.setCategory(category);
    sportequipment.setSporttype(sporttype);
    sportequipment.setEmployee(null);
    
    Set<ConstraintViolation<Sportequipment>> result = validator.validate(sportequipment);
    assertEquals(1, result.size());
    assertEquals("must not be null", result.iterator().next().getMessage());
    assertEquals("employee", result.iterator().next().getPropertyPath().toString());
}
@Test
void b6020163_testCategoryforSportequipmentMustNotBeNull() {
    Category category = new Category();
    category.setCategory_name("กีฬากลางแจ้ง");
    category = categoryRepository.saveAndFlush(category);

    Sporttype sporttype = new Sporttype();
    sporttype.setSport_type("กีฬากลางแจ้ง");
    sporttype = sporttypeRepository.saveAndFlush(sporttype);
    
    Phonetype phonetype = new Phonetype();
    phonetype.setPhonetype("มือถือส่วนตัว");
    phonetype = phonetypeRepository.saveAndFlush(phonetype);
    Position position = new Position();
    position.setPosition("พนักงานประจําเคาน์เตอร์");
    position = positionRepository.saveAndFlush(position);
    Province province = new Province();
    province.setProvince("นครสวรรค์");
    province = provinceRepository.saveAndFlush(province);
    Employee employee = new Employee();
    java.sql.Date date = new java.sql.Date(2020-02-05);
    employee.setTimeRegis(date);
    employee.setName("สมชาย นามสมมุติ");
    employee.setEmail("somsom@gmail.com");
    employee.setPassword("12345678");
    employee.setPhonenumber("0123456789");
    employee.setPhonetype(phonetype);
    employee.setPosition(position);
    employee.setProvince(province);
    employee = employeeRepository.saveAndFlush(employee);

    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand("adidas");
    sportequipment.setPrice("590");
    sportequipment.setDate(date);
    sportequipment.setSe_name("ลูกวอลเลย์บอล");
    sportequipment.setCategory(null);
    sportequipment.setSporttype(sporttype);
    sportequipment.setEmployee(employee);
    
    Set<ConstraintViolation<Sportequipment>> result = validator.validate(sportequipment);
    assertEquals(1, result.size());
    assertEquals("must not be null", result.iterator().next().getMessage());
    assertEquals("category", result.iterator().next().getPropertyPath().toString());
}
@Test
void b6020163_testSporttypeforSportequipmentMustNotBeNull() {
    Category category = new Category();
    category.setCategory_name("กีฬากลางแจ้ง");
    category = categoryRepository.saveAndFlush(category);

    Sporttype sporttype = new Sporttype();
    sporttype.setSport_type("กีฬากลางแจ้ง");
    sporttype = sporttypeRepository.saveAndFlush(sporttype);
    
    Phonetype phonetype = new Phonetype();
    phonetype.setPhonetype("มือถือส่วนตัว");
    phonetype = phonetypeRepository.saveAndFlush(phonetype);
    Position position = new Position();
    position.setPosition("พนักงานประจําเคาน์เตอร์");
    position = positionRepository.saveAndFlush(position);
    Province province = new Province();
    province.setProvince("นครสวรรค์");
    province = provinceRepository.saveAndFlush(province);
    Employee employee = new Employee();
    java.sql.Date date = new java.sql.Date(2020-02-05);
    employee.setTimeRegis(date);
    employee.setName("สมชาย นามสมมุติ");
    employee.setEmail("somsom@gmail.com");
    employee.setPassword("12345678");
    employee.setPhonenumber("0123456789");
    employee.setPhonetype(phonetype);
    employee.setPosition(position);
    employee.setProvince(province);
    employee = employeeRepository.saveAndFlush(employee);

    Sportequipment sportequipment = new Sportequipment();
    sportequipment.setBrand("adidas");
    sportequipment.setPrice("590");
    sportequipment.setDate(date);
    sportequipment.setSe_name("ลูกวอลเลย์บอล");
    sportequipment.setCategory(category);
    sportequipment.setSporttype(null);
    sportequipment.setEmployee(employee);
    
    Set<ConstraintViolation<Sportequipment>> result = validator.validate(sportequipment);
    assertEquals(1, result.size());
    assertEquals("must not be null", result.iterator().next().getMessage());
    assertEquals("sporttype", result.iterator().next().getPropertyPath().toString());
}

}