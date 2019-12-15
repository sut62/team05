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
@Table(name = "MEMBERS")
public class Members {
    @Id
    @SequenceGenerator(name = "MEMBERS_SEQ", sequenceName = "MEMBERS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBERS_SEQ")
    private Long id;
    private String member_id;
    private String name;
    private String address;
    private String email;
    private String phonenumber;

    public Members() {
    }

    public void setName(String name) {
        this.name = name;
    }
}
