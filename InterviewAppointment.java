package com.cpe.backend.interview.entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import com.cpe.backend.application.entity.Application;
import com.cpe.backend.registercompany.entity.Company;
import com.cpe.backend.registercompany.entity.Province;



@Data
@Entity
@NoArgsConstructor
@Table(name="InterviewAppointment")
public class InterviewAppointment {
	@Id
	@SequenceGenerator(name="Interview_seq",sequenceName="Interview_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Interview_seq")
	@Column(name="Interview_ID",unique = true, nullable = true)
	private @NonNull Long id;

	@Column(name="Interview_Date")
	private @NonNull Date date;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Company.class)
	@JoinColumn(name = "Company_ID", insertable = true)
	private Company company;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Application.class)
	@JoinColumn(name = "APPLICATION_ID", insertable = true)
	private Application application;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
	@JoinColumn(name = "Province_ID", insertable = true)
	private Province province;

	public void setCompany(Company company) {
		this.company=company;
	}
	public void setApplication(Application application) {
		this.application=application;
	}
	public void setProvince(Province province) {
		this.province=province;
	}
	public void setInterview(Date date) {
		this.date=date;
	}
}
