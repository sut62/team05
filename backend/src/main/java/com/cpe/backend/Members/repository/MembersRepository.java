package com.cpe.backend.Members.repository;

import com.cpe.backend.Members.entity.Members;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RepositoryRestResource
public interface MembersRepository extends JpaRepository<Members, Long> {
    Members findById(long id);

    // @Query(value = "SELECT * FROM MEMBERS where address = :username", nativeQuery = true)
    // Members findusername(@Param("username") String i);

    @Query(value ="SELECT * FROM MEMBERS u WHERE u.address = :username", nativeQuery = true)
    Members findusername(@Param("username") String status);
}

// SELECT POSITION FROM JOBPOST,POSITION WHERE JOBPOST.POSITION_ID =
// POSITION.POSITION_ID
