package com.cpe.backend;

import com.cpe.backend.Reservation.entity.Reservation;
import com.cpe.backend.Reservation.repository.ReservationRepository;
import com.cpe.backend.Reservation.entity.Fieldtype;
import com.cpe.backend.Reservation.repository.FieldtypeRepository;
import com.cpe.backend.Reservation.entity.Fielduse;
import com.cpe.backend.Reservation.repository.FielduseRepository;

import com.cpe.backend.Employee.entity.Employee;
import com.cpe.backend.Employee.repository.EmployeeRepository;
import com.cpe.backend.Employee.entity.Phonetype;
import com.cpe.backend.Employee.repository.PhonetypeRepository;
import com.cpe.backend.Employee.entity.Position;
import com.cpe.backend.Employee.repository.PositionRepository;

import com.cpe.backend.Members.entity.Members;
import com.cpe.backend.Members.repository.MembersRepository;
import com.cpe.backend.Members.entity.Gender;
import com.cpe.backend.Members.repository.GenderRepository;
import com.cpe.backend.Members.entity.Nametype;
import com.cpe.backend.Members.repository.NametypeRepository;
import com.cpe.backend.Members.entity.Province;
import com.cpe.backend.Members.repository.ProvinceRepository;

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
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class ReservationTest {
    private Validator validator;

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private MembersRepository membersRepository;
    @Autowired
    private FieldtypeRepository fieldtypeRepository;
    @Autowired
    private FielduseRepository fielduseRepository;
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



    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
   
   //   test การใส่ข้อมูลครบของ Reservation
    @Test
    void b6008970_testResvertionOK() {
        //=========Employee====================== */
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
        employee.setEmail("som@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province1);
        employee = employeeRepository.saveAndFlush(employee);

        //=========Members====================== */
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
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);
        members = membersRepository.saveAndFlush(members);

        //========Fielduse========================== */

        Fielduse fielduse = new Fielduse();
        fielduse.setFielduse_name("ปกติ");
        fielduse = fielduseRepository.saveAndFlush(fielduse);

        //========Fieldtype========================== */

        Fieldtype fieldtype = new Fieldtype();
        fieldtype.setFieldtype_name("สนามกรีฑา");
        fieldtype = fieldtypeRepository.saveAndFlush(fieldtype);
        Reservation r = new Reservation();
        long milli = 123456789999l; 
        java.sql.Date dateReseration = new java.sql.Date(2020-02-05);
        java.sql.Time time = new java.sql.Time(milli);
        r.setEmployee(employee);
        r.setMembers(members);
        r.setFieldtype(fieldtype);
        r.setFielduse(fielduse);
        r.setDate(dateReseration);
        r.setStart_time(time);
        r.setEnd_time(time);
        r = reservationRepository.saveAndFlush(r);
        Optional<Reservation> found = reservationRepository.findById(r.getReservation_id());
        assertEquals(employee, found.get().getEmployee());
        assertEquals(members, found.get().getMembers());
        assertEquals(fieldtype, found.get().getFieldtype());
        assertEquals(fielduse, found.get().getFielduse());
        assertEquals(r.getDate(), found.get().getDate());
        assertEquals(r.getStart_time(), found.get().getStart_time());
        assertEquals(r.getEnd_time(), found.get().getEnd_time());
    }
    //   test การใส่ข้อมูลของ employee ต้องไม่เป็น notnull
    @Test
    void b6008970_testEmployeeNotNullReservation() {
        //=========Employee====================== */
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
        employee.setEmail("som@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province1);
        employee = employeeRepository.saveAndFlush(employee);

        //=========Members====================== */
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
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);
        members = membersRepository.saveAndFlush(members);

        //========Fielduse========================== */

        Fielduse fielduse = new Fielduse();
        fielduse.setFielduse_name("ปกติ");
        fielduse = fielduseRepository.saveAndFlush(fielduse);

        //========Fieldtype========================== */

        Fieldtype fieldtype = new Fieldtype();
        fieldtype.setFieldtype_name("สนามกรีฑา");
        fieldtype = fieldtypeRepository.saveAndFlush(fieldtype);
        Reservation r = new Reservation();
        long milli = 123456789999l; 
        java.sql.Date dateReseration = new java.sql.Date(2020-02-05);
        java.sql.Time time = new java.sql.Time(milli);

        r.setEmployee(null);
        r.setMembers(members);
        r.setFieldtype(fieldtype);
        r.setFielduse(fielduse);
        r.setDate(dateReseration);
        r.setStart_time(time);
        r.setEnd_time(time);
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Reservation>> result = validator.validate(r);
        assertEquals(1, result.size());
        ConstraintViolation<Reservation> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("employee", v.getPropertyPath().toString());
    }
    //   test การใส่ข้อมูลของ Members ต้องไม่เป็น notnull
    @Test
    void b6008970_testMembersNotNullReservation() {
        //=========Employee====================== */
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
        employee.setEmail("som@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province1);
        employee = employeeRepository.saveAndFlush(employee);

        //=========Members====================== */
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
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);
        members = membersRepository.saveAndFlush(members);

        //========Fielduse========================== */

        Fielduse fielduse = new Fielduse();
        fielduse.setFielduse_name("ปกติ");
        fielduse = fielduseRepository.saveAndFlush(fielduse);

        //========Fieldtype========================== */

        Fieldtype fieldtype = new Fieldtype();
        fieldtype.setFieldtype_name("สนามกรีฑา");
        fieldtype = fieldtypeRepository.saveAndFlush(fieldtype);

        Reservation r = new Reservation();
        long milli = 123456789999l; 
        java.sql.Date dateReseration = new java.sql.Date(2020-02-05);
        java.sql.Time time = new java.sql.Time(milli);

        r.setEmployee(employee);
        r.setMembers(null);
        r.setFieldtype(fieldtype);
        r.setFielduse(fielduse);
        r.setDate(dateReseration);
        r.setStart_time(time);
        r.setEnd_time(time);
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Reservation>> result = validator.validate(r);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("members", result.iterator().next().getPropertyPath().toString());
    }
    //   test การใส่ข้อมูลFielduseต้องไม่เป็น Notnull ของ Reservation
    @Test
    void b6008970_testFielduseResvertionNotNull() {
        //=========Employee====================== */
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
        employee.setEmail("som@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province1);
        employee = employeeRepository.saveAndFlush(employee);

        //=========Members====================== */
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
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);
        members = membersRepository.saveAndFlush(members);

        //========Fielduse========================== */

        Fielduse fielduse = new Fielduse();
        fielduse.setFielduse_name("ปกติ");
        fielduse = fielduseRepository.saveAndFlush(fielduse);

        //========Fieldtype==========================

        Fieldtype fieldtype = new Fieldtype();
        fieldtype.setFieldtype_name("สนามกรีฑา");
        fieldtype = fieldtypeRepository.saveAndFlush(fieldtype);

        Reservation r = new Reservation();
        long milli = 123456789999l; 
        java.sql.Date dateReseration = new java.sql.Date(2020-02-05);
        java.sql.Time time = new java.sql.Time(milli);

        r.setEmployee(employee);
        r.setMembers(members);
        r.setFieldtype(fieldtype);
        r.setFielduse(null);
     
        r.setDate(dateReseration);
        r.setStart_time(time);
        r.setEnd_time(time);
        Set<ConstraintViolation<Reservation>> result = validator.validate(r);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("fielduse", result.iterator().next().getPropertyPath().toString());
    }

    //   test การใส่ข้อมูล Fieldtype ต้องไม่เป็น Notnull ของ Reservation
    @Test
    void b6008970_testFieldtypeResvertionNotNull() {
        //=========Employee======================*/ 
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
        employee.setEmail("som@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province1);
        employee = employeeRepository.saveAndFlush(employee);

        //=========Members====================== */
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
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);
        members = membersRepository.saveAndFlush(members);

        //========Fielduse========================== */

        Fielduse fielduse = new Fielduse();
        fielduse.setFielduse_name("ปกติ");
        fielduse = fielduseRepository.saveAndFlush(fielduse);

        //========Fieldtype========================== */

        Fieldtype fieldtype = new Fieldtype();
        fieldtype.setFieldtype_name("สนามกรีฑา");
        fieldtype = fieldtypeRepository.saveAndFlush(fieldtype);
        Reservation r = new Reservation();
        long milli = 123456789999l; 
        java.sql.Date dateReseration = new java.sql.Date(2020-02-05);
        java.sql.Time time = new java.sql.Time(milli);

        r.setEmployee(employee);
        r.setMembers(members);
        r.setFieldtype(null);
        r.setFielduse(fielduse);
     
        r.setDate(dateReseration);
        r.setStart_time(time);
        r.setEnd_time(time);
        Set<ConstraintViolation<Reservation>> result = validator.validate(r);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("fieldtype", result.iterator().next().getPropertyPath().toString());
    }


     //   test การใส่ข้อมูลdateต้องไม่เป็น Notnull ของ Reservation
    @Test
    void b6008970_testDateResvertionNotNull() {
        //=========Employee====================== */
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
        employee.setEmail("som@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province1);
        employee = employeeRepository.saveAndFlush(employee);

        //=========Members====================== */
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
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);
        members = membersRepository.saveAndFlush(members);

        //========Fielduse========================== */

        Fielduse fielduse = new Fielduse();
        fielduse.setFielduse_name("ปกติ");
        fielduse = fielduseRepository.saveAndFlush(fielduse);

        //========Fieldtype========================== */

        Fieldtype fieldtype = new Fieldtype();
        fieldtype.setFieldtype_name("สนามกรีฑา");
        fieldtype = fieldtypeRepository.saveAndFlush(fieldtype);
        Reservation r = new Reservation();
        long milli = 123456789999l; 
        java.sql.Date dateReseration = new java.sql.Date(2020-02-05);
        java.sql.Time time = new java.sql.Time(milli);

        r.setEmployee(employee);
        r.setMembers(members);
        r.setFieldtype(fieldtype);
        r.setFielduse(fielduse);
        r.setDate(null);
        r.setStart_time(time);
        r.setEnd_time(time);
        Set<ConstraintViolation<Reservation>> result = validator.validate(r);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("date", result.iterator().next().getPropertyPath().toString());
    }
     //   test การใส่ข้อมูล start_time ต้องไม่เป็น Notnull ของ Reservation
    @Test
    void b6008970_testStarttimeResvertionNotNull() throws ParseException{
        //=========Employee====================== */
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
        employee.setEmail("som@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province1);
        employee = employeeRepository.saveAndFlush(employee);

        //=========Members====================== */
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
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);
        members = membersRepository.saveAndFlush(members);

        //========Fielduse========================== */

        Fielduse fielduse = new Fielduse();
        fielduse.setFielduse_name("ปกติ");
        fielduse = fielduseRepository.saveAndFlush(fielduse);

        //========Fieldtype========================== */

        Fieldtype fieldtype = new Fieldtype();
        fieldtype.setFieldtype_name("สนามกรีฑา");
        fieldtype = fieldtypeRepository.saveAndFlush(fieldtype);
        Reservation r = new Reservation();
        long milli = 123456789999l; 
        java.sql.Date dateReseration = new java.sql.Date(2020-02-05);
        java.sql.Time time = new java.sql.Time(milli);

        r.setEmployee(employee);
        r.setMembers(members);
        r.setFieldtype(fieldtype);
        r.setFielduse(fielduse);
        r.setDate(dateReseration);
        r.setStart_time(null);
        r.setEnd_time(time);
        Set<ConstraintViolation<Reservation>> result = validator.validate(r);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("Start_time", result.iterator().next().getPropertyPath().toString());
    }
    //   test การใส่ข้อมูล start_time ต้องไม่เป็น Notnull ของ Reservation
    @Test
    void b6008970_testEndtimeResvertionNotNull() throws ParseException {
        //=========Employee====================== */
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
        employee.setEmail("som@gmail.com");
        employee.setPassword("12345678");
        employee.setPhonenumber("0123456789");
        employee.setPhonetype(phonetype);
        employee.setPosition(position);
        employee.setProvince(province1);
        employee = employeeRepository.saveAndFlush(employee);

        //=========Members====================== */
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
        members.setGender(gender);
        members.setNametype(nametype);
        members.setProvince(province);
        members = membersRepository.saveAndFlush(members);

        //========Fielduse========================== */

        Fielduse fielduse = new Fielduse();
        fielduse.setFielduse_name("ปกติ");
        fielduse = fielduseRepository.saveAndFlush(fielduse);

        //========Fieldtype========================== */

        Fieldtype fieldtype = new Fieldtype();
        fieldtype.setFieldtype_name("สนามกรีฑา");
        fieldtype = fieldtypeRepository.saveAndFlush(fieldtype);

        Reservation r = new Reservation();
        long milli = 123456789999l; 
        java.sql.Date dateReseration = new java.sql.Date(2020-02-05);
        java.sql.Time time = new java.sql.Time(milli);

        r.setEmployee(employee);
        r.setMembers(members);
        r.setFieldtype(fieldtype);
        r.setFielduse(fielduse);
        r.setDate(dateReseration);
        r.setStart_time(time);
        r.setEnd_time(null);
        Set<ConstraintViolation<Reservation>> result = validator.validate(r);
        assertEquals(1, result.size());
        assertEquals("must not be null", result.iterator().next().getMessage());
        assertEquals("End_time", result.iterator().next().getPropertyPath().toString());
    }
    
   

}