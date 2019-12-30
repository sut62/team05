package com.cpe.backend.Employee.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="EMPLOYEE")
public class Employee {
    @Id
    @SequenceGenerator(name="EMPLOYEE_SEQ",sequenceName="EMPLOYEE_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMPLOYEE_SEQ")
    @Column(name="EMPLOYEE_ID",unique = true, nullable = true)
    private @NonNull Long emp_id;
    @Column(name="NAME")
    private @NonNull String name;
    @Column(name="Date")
    private @NonNull Date date;
    @Column(name="EMAIL")
    private @NonNull String email;
    @Column(name="PASSWORD")
    private @NonNull String password;
    @Column(name="PHONENUMBER")
    private @NonNull String phonenumber;
    

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Phonetype.class)
    @JoinColumn(name = "PHONETYPE_ID", insertable = true)
    private Phonetype phonetype;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "PROVINCE_ID", insertable = true)
    private Province province;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Position.class)
    @JoinColumn(name = "POSITION_ID", insertable = true)
    private Position position;

	public void setName(String name) {
    this.name=name;
	}
	public void setDate(Date date) {
        this.date=date;
	}

	public void setEmail(String email) {
        this.email=email;
        }
        
        public void setPassword(String password) {
                this.password=password;
        }

	public void setPhonenumber(String phonenumber) {
        this.phonenumber=phonenumber;
	}

	public void setPhonetype(Phonetype phonetype) {
        this.phonetype=phonetype;
	}

	public void setProvince(Province province) {
        this.province=province;
	}
	public void setPosition(Position position) {
        this.position = position;
	}
	


	
}