package com.cpe.backend.entity;

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
@Table(name="BOOK")
public class Book {
    @Id    
    @SequenceGenerator(name="BOOK_SEQ",sequenceName="BOOK_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BOOK_SEQ")  
     @Column(name = "BOOK_ID", unique = true, nullable = true)
    private @NonNull Long id;   
    @Column(name="BOOK_name")
    private @NonNull String name;

    @OneToMany(fetch = FetchType.EAGER)
    // mappedBy  = "createdBy"
    private Collection<Status> rent;

	

	

	
}