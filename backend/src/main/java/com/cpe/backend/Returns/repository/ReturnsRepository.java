package com.cpe.backend.Returns.repository;

import com.cpe.backend.Returns.entity.Returns;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.repository.query.Param;
import java.util.Collection;

@CrossOrigin(origins = "http://localhost:8080")
@RepositoryRestResource
public interface ReturnsRepository extends JpaRepository<Returns, Long> {
    Returns findById(long id);
    @Query( value = "SELECT * FROM Return z WHERE z.a = :email",nativeQuery = true)
    Collection<Returns> findCheck(@Param("email") String email);
}