package com.cpe.backend.Members.entity;

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
@Table(name="GENDER")
public class Gender {
    @Id
    @SequenceGenerator(name="GENDER_SEQ",sequenceName="GENDER_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GENDER_SEQ")
    @Column(name="GENDER_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String gender;
    
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Members> members;
    
	public void setGender(String gender) {
        this.gender=gender;
	}
}