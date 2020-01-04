package com.cpe.backend.Sportequipment.entity;


import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	@Column(name="Category_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String category_name;


	@OneToMany(fetch = FetchType.EAGER)
	private Collection<Sportequipment> sportequipment;

	public void setCategory(String category_name) {
			this.category_name=category_name;
	}

	

}