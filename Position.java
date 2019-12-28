package com.sut.sa.g13.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
@Table(name="POSITION")
public class Position {
	@Id
	@SequenceGenerator(name="position_seq",sequenceName="position_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="position_seq")
	@Column(name="POSITION_ID",unique = true, nullable = true)
	private @NonNull Long positionid;
	private @NonNull String positionlevel;

}
