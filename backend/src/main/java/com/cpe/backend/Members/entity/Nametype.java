package com.cpe.backend.Members.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Data
@Entity
@NoArgsConstructor
@Table(name="NAMETYPE")
public class Nametype {
    @Id
    @SequenceGenerator(name="NAMETYPE_SEQ",sequenceName="NAMETYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="NAMETYPE_SEQ")
    private @NonNull Long nametype_id;
    @NotNull
    private @NonNull String nametype;
    
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Members> members;
    
	public void setNametype(String nametype) {
        this.nametype=nametype;
	}
}