package com.cpe.backend.Reservation.repository;

import java.util.Collection;

import com.cpe.backend.Reservation.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findById(long id);
}