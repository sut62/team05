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
@Table(name="ADMINID")
public class AdminID {
    @Id
    @SequenceGenerator(name="ADMINID_SEQ",sequenceName="ADMINID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ADMINID_SEQ")
    @Column(name="ADMINID_ID",unique = true, nullable = true)
    private @NonNull Long adminID_id;
    private @NonNull String adminID;
    private @NonNull String adminpass;

    
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Employee> employees;
    
	public void setAdminID(String adminID) {
        this.adminID=adminID;
    }
  
}