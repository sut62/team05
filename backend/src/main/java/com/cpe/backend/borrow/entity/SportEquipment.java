package com.cpe.backend.borrow.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
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
@Table(name = "SPORTEQUIPMENT")
public class SportEquipment {
    @Id
    @SequenceGenerator(name = "SPORTEQUIPMENT_SEQ", sequenceName = "SPORTEQUIPMENT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPORTEQUIPMENT_SEQ")
    private Long se_id;
    private String brand;
    private String price;
    private Date Date;
    private String se_name;

    public SportEquipment() {
    }

    public void setName(String name) {
        this.se_name = name;
    }

}
