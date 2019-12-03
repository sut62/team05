package com.cpe.backend.jobpost.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.cpe.backend.registercompany.entity.Company;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="JOBPOST")
public class JobPost {
    @Id
    @SequenceGenerator(name="JOBPOSTP_SEQ",sequenceName="JOBPOSTP_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="JOBPOSTP_SEQ")
    @Column(name="JOBPOSTP_ID",unique = true, nullable = true)
    private @NonNull Long JobPost_id;
    @Column(name="SARALY")
    private @NonNull String salary;
    @Column(name="EDUCATIONLEVEL")
    private @NonNull String educationlevel;
    @Column(name="OTHER_DETAILS")
    private @NonNull String Other_details;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Company.class)
    @JoinColumn(name = "Company_ID", insertable = true)
    private Company company;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Benefits.class)
    @JoinColumn(name = "BENEFITS_ID", insertable = true)
    private Benefits benefits;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Position.class)
    @JoinColumn(name = "POSITION_ID", insertable = true)
    private Position position;

	public void setBenefits(Benefits benefits) {
        this.benefits=benefits;
	}

	public void setCompany(Company company) {
        this.company=company;
	}

	public void setPosition(Position position) {
        this.position=position;
        }

	public void setOther(String other_details) {
                this.Other_details=other_details;
	}

	public void setEducationlevel(String educationlevel) {
                this.educationlevel=educationlevel;
	}

	public void setSalary(String salary) {
                this.salary=salary;
	}
	
}