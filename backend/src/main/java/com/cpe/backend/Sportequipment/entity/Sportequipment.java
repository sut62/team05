package com.cpe.backend.Sportequipment.entity;

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

import com.cpe.backend.Employee.entity.Employee;


@Data
@Entity
@NoArgsConstructor
@Table(name = "Sportequipment")
public class Sportequipment {
    @Id
    @SequenceGenerator(name = "Sportequipment_seq", sequenceName = "Sportequipment_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sportequipment_seq")

    private Long id;

    @Size(min = 3,max = 15)
    @NotNull
    @Pattern(regexp = "[a-zA-Z]{1,20}$")
    private String brand;

    @NotNull
    private String price;

    @NotNull
    private Date date;

    @NotNull
    @Pattern(regexp = "[ก-๙ a-zA-Z]*")
    private String se_name;

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

	public void setPrice(String price) {
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
