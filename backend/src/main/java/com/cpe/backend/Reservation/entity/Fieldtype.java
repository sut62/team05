package com.cpe.backend.Reservation.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
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
    private @NonNull Long id;
    
    @Column(name="Fieldtype_name")
    private @NonNull String Fieldtype_name;
    //@OneToOne(fetch = FetchType.EAGER)
    //private Collection<Apply> apply;

    public void setName(String name){
        this.Fieldtype_name=name;
    }
}