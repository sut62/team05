package com.cpe.backend.Reservation.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.sql.Date;
import java.sql.Time;
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
    private @NonNull Long id;

    @Column(name = "Reservation_Date")
    private @NonNull Date date;

    @Column(name = "Start_time")
    private @NonNull Time Start_time;

    @Column(name = "End_time")
    private @NonNull Time End_time;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Fieldtype.class)
    @JoinColumn(name = "Fieldtype_id", insertable = true)
    @JsonManagedReference
    private Fieldtype fieldtype;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Fielduse.class)
    @JoinColumn(name = "Fielduse_id", insertable = true)
    @JsonManagedReference
    private Fielduse fielduse;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Members.class)
    @JoinColumn(name = "member_id", insertable = true)
    @JsonManagedReference
    private  Members members;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "emp_id", insertable = true)
    @JsonManagedReference
    private  Employee employee;

	public void setStart_time(Time start_time2) {
        this.Start_time = start_time2;
	}

	public void setEnd_time(Time end_time2) {
        this.End_time = end_time2;
	}
	
}