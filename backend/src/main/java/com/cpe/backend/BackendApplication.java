package com.cpe.backend;

import com.cpe.backend.Members.entity.Nametype;
import com.cpe.backend.Members.entity.Gender;
import com.cpe.backend.Members.entity.Province;
import com.cpe.backend.Members.repository.GenderRepository;
import com.cpe.backend.Members.repository.NametypeRepository;
import com.cpe.backend.Members.repository.ProvinceRepository;
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
	ApplicationRunner init(ProvinceRepository provinceRepository,NametypeRepository nametypeRepository,GenderRepository genderRepository) {
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


			
		
			genderRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Customer บน Terminal
			provinceRepository.findAll().forEach(System.out::println); 
			nametypeRepository.findAll().forEach(System.out::println); 
			
		};
	}

}
