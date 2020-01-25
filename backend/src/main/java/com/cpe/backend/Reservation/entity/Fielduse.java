package com.cpe.backend.Reservation.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name= "Fielduse")
public class Fielduse {
    @Id    
    @SequenceGenerator(name="Fielduse_SEQ",sequenceName="Fielduse_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Fielduse_SEQ")

    @Column(name="Fielduse_id",unique = true, nullable = true)
    private @NonNull Long Fielduse_id;

    @NotNull
    @Size(min=4, max=10)
    @Pattern(regexp = "^[ก-๙เ]*$")
    private  String Fielduse_name;
    @OneToMany(fetch = FetchType.EAGER)
	
	private Set<Reservation> Fielduse;

    public void setName(String name){
        this.Fielduse_name=name;
    }

	
	
    
}