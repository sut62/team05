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
@Table(name="PROVINCE")
public class Province {
    @Id
    @SequenceGenerator(name="PROVINCE_SEQ",sequenceName="PROVINCE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PROVINCE_SEQ")
    @Column(name="PROVINCE_ID",unique = true, nullable = true)
    private @NonNull Long province_id;
    private @NonNull String province;
    
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Members> members;
    
	public void setProvince(String province) {
        this.province=province;
	}
}