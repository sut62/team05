package com.cpe.backend.Reservation.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.sql.*;

import com.cpe.backend.Members.entity.Members;
import com.cpe.backend.Employee.entity.Employee;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
@NoArgsConstructor
@Table(name="Reservation")
public class Reservation{
    @Id
    @SequenceGenerator(name="Reservation_SEQ",sequenceName="Reservation_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Reservation_SEQ")
    @Column(name = "Reservation_id", unique = true, nullable = true)
    private @NonNull Long Reservation_id;
    
    @NotNull
    private  Date date;
    @NotNull
    @Column(name = "Start_time")
    private  Time Start_time;
    @NotNull
    @Column(name = "End_time")
    private  Time End_time;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Fieldtype.class)
    @JoinColumn(name = "Fieldtype_id", insertable = true)
    @JsonManagedReference
    @NotNull
    private Fieldtype fieldtype;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Fielduse.class)
    @JoinColumn(name = "Fielduse_id", insertable = true)
    @JsonManagedReference
    @NotNull
    private Fielduse fielduse;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Members.class)
    @JoinColumn(name = "member_id", insertable = true)
    @JsonManagedReference
    @NotNull
    private  Members members;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "emp_id", insertable = true)
    @JsonManagedReference
    @NotNull
    private  Employee employee;

	public void setStart_time(Time start_time2) {
        this.Start_time = start_time2;
	}

	public void setEnd_time(Time end_time2) {
        this.End_time = end_time2;
	}

	public void setFieldtype(Fieldtype fieldtype) {
                this.fieldtype=fieldtype;
	}

	public void setFielduse(Fielduse fielduse) {
                this.fielduse=fielduse;
	}

	public void setMembers(Members members) {
                this.members=members;
	}

	public void setEmployee(Employee employee) {
                this.employee=employee;
	}

	public void setDate(Date date) {
                this.date=date;
	}

	

	

}