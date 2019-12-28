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
@Table(name="Sporttype")
public class Sporttype {
    @Id
    @SequenceGenerator(name="Sporttype_seq",sequenceName="Sporttype_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Sporttype_seq")
    @Column(name="Sporttype_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String sport_type;
   

    @OneToMany(fetch = FetchType.EAGER)
	// mappedBy  = "employee"
	private Collection<Sportequipment> sportequipment;

	public void setSporttype(String sport_type) {
        this.sport_type=sport_type;
	}


    


    
}