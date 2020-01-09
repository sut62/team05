package com.cpe.backend.borrow.repository;

import java.util.Collection;

import com.cpe.backend.borrow.entity.Borrow;
import com.cpe.backend.Members.entity.Members;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    Borrow findByMembers(Members members);

    Borrow findById(long id);

}
