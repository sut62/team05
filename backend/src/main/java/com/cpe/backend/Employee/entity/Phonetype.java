package com.cpe.backend.Employee.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Data
@Entity
@NoArgsConstructor
@Table(name="PHONETYPE")
public class Phonetype {
    @Id
    @SequenceGenerator(name="PHONETYPE_SEQ",sequenceName="PHONETYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PHONETYPE_SEQ")
    @Column(name="PHONETYPE_ID",unique = true, nullable = true)
    private @NonNull Long phonetype_id;
    private @NonNull String phonetype;
    
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Employee> employees;
    
	public void setPhonetype(String phonetype) {
        this.phonetype=phonetype;
	}
}