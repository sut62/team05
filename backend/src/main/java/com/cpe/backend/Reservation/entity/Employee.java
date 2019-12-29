package com.cpe.backend.Reservation.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;


@Data
@Entity
@NoArgsConstructor
@Table(name="Employee")
public class Employee {
    @Id    
    @SequenceGenerator(name="Employee_SEQ",sequenceName="Employee_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Employee_SEQ")

    @Column(name="emp_id",unique = true, nullable = true)
    private @NonNull Long emp_id;
    
    @Column(name="TimeRegister")
    private @NonNull Date TimeRegister;


    @Column(name="Name")
    private @NonNull String name;

    @Column(name="Email")
    private @NonNull String email;

    @Column(name="Password")
    private @NonNull String password;
    
    @Column(name="Phonenumber")
    private @NonNull String phonenumber;

}