package com.cpe.backend.borrow.repository;

import com.cpe.backend.borrow.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MembersRepository extends JpaRepository<Members, Long> {
    Members findById(long id);
}
