package com.cpe.backend.borrow.entity;

import lombok.*;

import java.sql.Date;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    @SequenceGenerator(name = "EMPLOYEE_SEQ", sequenceName = "EMPLOYEE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_SEQ")
    private Long emp_id;

    private Date registertime;
    private String email;
    private String password;
    private String name;
    private String phonenumber;

    public Employee() {
    }

    public void setName(String name) {
        this.name = name;
    }

}
