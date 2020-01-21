package com.cpe.backend.Reservation.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="Fieldtype")
public class Fieldtype {
    @Id    
    @SequenceGenerator(name="Fieldtype_SEQ",sequenceName="Fieldtype_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Fieldtype_SEQ")

    @Column(name="Fieldtype_id",unique = true, nullable = true)
    private @NonNull Long Fieldtype_id;

    @NotNull
    @Size(min=4, max=30)
    private  String Fieldtype_name;
    @OneToMany(fetch = FetchType.EAGER)
	
	private Set<Reservation> Fieldtype;

    public void setName(String name){
        this.Fieldtype_name=name;
    }

}