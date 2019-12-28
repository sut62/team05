package com.cpe.backend;

import com.cpe.backend.Members.entity.Nametype;
import com.cpe.backend.Members.entity.Gender;
import com.cpe.backend.Members.entity.Province;
import com.cpe.backend.Returns.entity.Status;
import com.cpe.backend.Employee.entity.Position;
import com.cpe.backend.Employee.entity.Phonetype;
import com.cpe.backend.Employee.entity.Province;

import com.cpe.backend.Members.repository.GenderRepository;
import com.cpe.backend.Members.repository.NametypeRepository;
import com.cpe.backend.Members.repository.ProvinceRepository;
import com.cpe.backend.Returns.repository.StatusRepository;
import com.cpe.backend.Employee.repository.PositionRepository;
import com.cpe.backend.Employee.repository.PhonetypeRepository;
import com.cpe.backend.Employee.repository.ProvinceRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	ApplicationRunner init(ProvinceRepository provinceRepository,NametypeRepository nametypeRepository,GenderRepository genderRepository,StatusRepository statusRepository,PositionRepository positionRepository,PhonetypeRepository phonetypeRepository) {
		return args -> {
			Stream.of("นาย", "นาง","นางสาว").forEach(name -> {
				Nametype nameType = new Nametype();
				nameType.setNametype(name);
				nametypeRepository.save(nameType);
			});

			Stream.of("ชาย", "หญิง").forEach(name -> {
				Gender gender = new Gender();
				gender.setGender(name);
				genderRepository.save(gender);
			});
		
			Stream.of("กรุงเทพมหานคร","กระบี่","กาญจนบุรี","กาฬสินธุ์","กำแพงเพชร","ขอนแก่น","จันทบุรี","ฉะเชิงเทรา","ชัยนาท","ชัยภูมิ","ชุมพร"
			,"ชลบุรี","เชียงใหม่","เชียงราย","ตรัง","ตราด","ตาก","นครนายก","นครปฐม","นครพนม","นครราชสีมา","นครศรีธรรมราช"
			,"นครสวรรค์","นราธิวาส","น่าน","นนทบุรี","บึงกาฬ","บุรีรัมย์","ประจวบคีรีขันธ์","ปทุมธานี","ปราจีนบุรี","ปัตตานี","พะเยา"
			,"พระนครศรีอยุธยา","พังงา","พิจิตร","พิษณุโลก","เพชรบุรี","เพชรบูรณ์","แพร่","พัทลุง","ภูเก็ต","มหาสารคาม","มุกดาหาร"
			,"แม่ฮ่องสอน","ยโสธร","ยะลา","ร้อยเอ็ด","ระนอง","ระยอง","ราชบุรี","ลพบุรี","ลำปาง","ลำพูน","เลย","ศรีสะเกษ","สกลนคร"
			,"สงขลา","สมุทรสาคร","สมุทรปราการ","สมุทรสงคราม","สระแก้ว","สระบุรี","สิงห์บุรี","สุโขทัย","สุพรรณบุรี","สุราษฎร์ธานี"
			,"สุรินทร์","สตูล","หนองคาย","หนองบัวลำภู","อำนาจเจริญ","อุดรธานี","อุตรดิตถ์","อุทัยธานี","อุบลราชธานี","อ่างทอง"
			
			).forEach(name -> {
				Province province = new Province();
				province.setProvince(name);
				provinceRepository.save(province);
			});
			Stream.of("ปรกติ", "เสียหาย").forEach(name -> {
				Status status = new Status();
				status.setName(name);
				statusRepository.save(status);
			});
			Stream.of("มือถือส่วนตัว", "โทรศัพท์บ้าน").forEach(name -> {
				Phonetype phonetype = new Phonetype();
				phonetype.setPhonetype(name);
				phonetypeRepository.save(phonetype);
			});

			Stream.of("พนักงานรักษาความปลอดภัย", "พนักงานประจําเคาน์เตอร์","พนักงานทําความสะอาด").forEach(name -> {
				Position position = new Position();
				position.setPosition(name);
				positionRepository.save(position);
			});

			
		
			genderRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Customer บน Terminal
			provinceRepository.findAll().forEach(System.out::println); 
			nametypeRepository.findAll().forEach(System.out::println);
			positionRepository.findAll().forEach(System.out::println);	
			phonetypeRepository.findAll().forEach(System.out::println);
			
		};
	}

}
