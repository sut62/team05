package com.cpe.backend.Returns.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="Status")
public class Status {
	@Id
	@SequenceGenerator(name="Status_seq",sequenceName="Status_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Status_seq")
	private @NonNull Long status_id;
	
	@NotNull
	@Size(min = 5,max = 10)
	@Pattern(regexp = "[ก-เ]*")
	private String statuss;
	
	@OneToMany(fetch = FetchType.EAGER)
	// mappedBy  = "Status"
	private Set<Returns> status;

	public void setName(String status){
		this.statuss = status;
	}

	
}