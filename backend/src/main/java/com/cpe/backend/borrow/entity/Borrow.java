package com.cpe.backend.borrow.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.time.LocalDate;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.format.annotation.DateTimeFormat;

import com.cpe.backend.Members.entity.Members;
import com.cpe.backend.Employee.entity.Employee;
import com.cpe.backend.Sportequipment.entity.Category;
import com.cpe.backend.Sportequipment.entity.Sportequipment;

@Data
@Entity
@Table(name = "BORROW")
public class Borrow {
    @Id
    @SequenceGenerator(name = "BORROW_SEQ", sequenceName = "BORROW_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BORROW_SEQ")
    private @NonNull Long borrow_id;
    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate borrow_date;

    @Size(min=5,max=20)
    private @NonNull String note;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Members.class)
    @JoinColumn(name = "MEMBERS_ID", insertable = true)
    @JsonManagedReference
    private @NonNull Members members;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    @JsonManagedReference   
    private @NonNull Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Category.class)
    @JoinColumn(name = "Category_ID", insertable = true)
    @JsonManagedReference
    private @NonNull Category category;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Sportequipment.class)
    @JoinColumn(name = "Sportequipment_ID", insertable = true)
    @JsonManagedReference
    private @NonNull Sportequipment sportequipment;

    public Borrow() {
    }

    public void setBorrow(LocalDate now) {
        this.borrow_date = now;
    }

	

	


}
