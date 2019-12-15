package com.cpe.backend.borrow.entity;

import lombok.*;

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
@Table(name = "CATEGORY")
public class Category {
    @Id
    @SequenceGenerator(name = "CATEGORY_SEQ", sequenceName = "CATEGORY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ")
    private Long category_id;
    private String category_name;

    public Category() {
    }

	public void setName(String name) {
        this.category_name = name;
	}

}
