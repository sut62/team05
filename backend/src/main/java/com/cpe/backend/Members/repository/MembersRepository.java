package com.cpe.backend.Members.repository;


import com.cpe.backend.Members.entity.Members;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface MembersRepository extends JpaRepository<Members, Long> {
    Members findById(long id);

}

//SELECT POSITION FROM JOBPOST,POSITION WHERE JOBPOST.POSITION_ID = POSITION.POSITION_ID
