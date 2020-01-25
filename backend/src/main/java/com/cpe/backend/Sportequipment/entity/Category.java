package com.cpe.backend.Sportequipment.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;


@Data
@Entity
@NoArgsConstructor
@Table(name="Category")
public class Category {
	@Id
	@SequenceGenerator(name="Category_seq",sequenceName="Category_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Category_seq")
	
	private Long id;
	
	@Size(min = 5,max = 20)
	@NotNull
	private String category_name;


	@OneToMany(fetch = FetchType.EAGER)
	private Collection<Sportequipment> sportequipment;

	public void setCategory(String category_name) {
			this.category_name=category_name;
	}

	

}