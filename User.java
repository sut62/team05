package com.cpe.backend.registeruser.entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
@Data
@Entity
@NoArgsConstructor
@Table(name="User")
public class User {
    @Id
    @SequenceGenerator(name="User_seq",sequenceName="User_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="User_seq")
    @Column(name = "User_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="Registertime")
    private @NonNull Date registertime;

    @Column(name="Name")
    private String name;

    @Column(name="Email")
    private  String email;

    @Column(name="Password")
    private  String password;

    @Column(name="Phone")
    private  String phone;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = NameType.class)
    @JoinColumn(name = "NameType_ID", insertable = true)
    private NameType nametype;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "Gender_ID", insertable = true)
    private Gender gender;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PhoneType.class)
    @JoinColumn(name = "PhoneType_ID", insertable = true)
    private PhoneType phoneType;


	public void setNameType(NameType nametype) {
        this.nametype = nametype;
	}

	public void setName(String names) {
        this.name = names;
	}

	public void setEmail(String emails) {
        this.email = emails;
	}

	public void setPassword(String passwords) {
        this.password = passwords;
	}

	public void setPhone(String phones) {
        this.phone = phones;
	}

	public void setGender(Gender gender) {
        this.gender = gender;
	}

	public void setPhoneType(PhoneType phonetype) {
        this.phoneType = phonetype;
	}
        
	public void setRegistertime(Date date) {
        this.registertime = date;
	}

    
}