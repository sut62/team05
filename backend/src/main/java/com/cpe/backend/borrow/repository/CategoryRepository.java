package com.cpe.backend.borrow.repository;

import com.cpe.backend.borrow.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findById(long id);
}
