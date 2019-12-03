package com.okta.entity.member;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@Table(name = "Member")
public class Member {
    @Id
    @SequenceGenerator(name = "Member_seq", sequenceName = "Member_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Member_seq")
    @Column(name = "Member_id", unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String name;

    private @NonNull String email;

    private @NonNull String password;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Title.class)
    @JoinColumn(name = "Title_id", insertable = true)
    @JsonManagedReference
    private @NotNull Title title;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Faculty.class)
    @JoinColumn(name = "Faculty_id", insertable = true)
    @JsonManagedReference
    private @NotNull Faculty faculty;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Education.class)
    @JoinColumn(name = "Education_id", insertable = true)
    @JsonManagedReference
    private @NotNull Education education;

    public Member() {
    }
}