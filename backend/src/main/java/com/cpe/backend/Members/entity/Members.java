package com.cpe.backend.Members.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="MEMBERS")
public class Members {
    @Id
    @SequenceGenerator(name="MEMBERS_SEQ",sequenceName="MEMBERS_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MEMBERS_SEQ")
    private @NonNull Long member_id;

    @NotNull
    @Size(min=5,max=15)
    @Pattern(regexp = "[A-Za-z0-9]{1,20}$")
    private String username;
    private @NonNull String name;
    private @NonNull Date date;
    private @NonNull String address;
    private @NonNull String email;
    private @NonNull String phonenumber;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Nametype.class)
    @JoinColumn(name = "NAMETYPE_ID", insertable = true)
    private Nametype nametype;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "PROVINCE_ID", insertable = true)
    private Province province;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "GENDER_ID", insertable = true)
    private Gender gender;

	public void setName(String name) {
    this.name=name;
	}
	public void setDate(Date date) {
        this.date=date;
	}
	public void setAddress(String address) {
        this.address=address;
	}

	public void setEmail(String email) {
        this.email=email;
	}

	public void setNametype(Nametype nametype) {
        this.nametype=nametype;
	}

	public void setPhonenumber(String phonenumber) {
        this.phonenumber=phonenumber;
	}

	public void setGender(Gender gender) {
        this.gender=gender;
	}

	public void setProvince(Province province) {
        this.province=province;
	}
	



	
}