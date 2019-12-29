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
@Table(name= "Fielduse")
public class Fielduse {
    @Id    
    @SequenceGenerator(name="Fielduse_SEQ",sequenceName="Fielduse_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Fielduse_SEQ")

    @Column(name="Fielduse_id",unique = true, nullable = true)
    private @NonNull Long id;
    @Column(name="Fielduse_name")
    private @NonNull String Fielduse_name;
    //@OneToOne(fetch = FetchType.EAGER)
    //private Collection<Apply> apply;

    public void setName(String name){
        this.Fielduse_name=name;
    }
    
}