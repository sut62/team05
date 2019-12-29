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
@Table(name="Member")
public class Member {
    @Id    
    @SequenceGenerator(name="Member_SEQ",sequenceName="Member_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Member_SEQ")

    @Column(name="id",unique = true, nullable = true)
    private @NonNull Long id;
    
    @Column(name="Member_id")
    private @NonNull String member_id;

    @Column(name="Name")
    private @NonNull String name;

    @Column(name="Address")
    private @NonNull String address;

    @Column(name="Email")
    private @NonNull String email;

    @Column(name="Phonenumber")
    private @NonNull String phonenumber;

    public void setName(String name){
        this.name=name;
    }

}