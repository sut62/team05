package com.cpe.backend.Sportequipment.entity;

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

import com.cpe.backend.Employee.entity.Employee;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Data
@Entity
@NoArgsConstructor
@Table(name = "Sportequipment")
public class Sportequipment {
    @Id
    @SequenceGenerator(name = "Sportequipment_seq", sequenceName = "Sportequipment_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sportequipment_seq")

    @Column(name="Sportequipment_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="Brand")
    private @NonNull String brand;
    @Column(name="Price")
    private @NonNull Long price;
    @Column(name="Date")
    private @NonNull Date date;
    @Column(name="Sname")
    private @NonNull String se_name;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Category.class)
    @JoinColumn(name = "Category_ID", insertable = true)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "Employee_ID", insertable = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Sporttype.class)
    @JoinColumn(name = "Sporttype_ID", insertable = true)
    private Sporttype sporttype;



	public void setBrand(String brand) {
        this.brand = brand;
	}
	public void setName(String se_name) {
        this.se_name = se_name;
	}

	public void setDate(Date date) {
        this.date =date;
	}

	public void setPrice(long price) {
        this.price = price;
	}

	public void setEmployee(Employee employee) {
        this.employee=employee;
	}

	public void setSporttype(Sporttype sporttype) {
        this.sporttype=sporttype;
	}

	public void setCategory(Category category) {
        this.category=category;
	}

}
