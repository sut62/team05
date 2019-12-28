package com.cpe.backend.Sportequipment.repository;

import com.cpe.backend.Sportequipment.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface CategoryRepository extends JpaRepository<Category, Long> {
	Category findById(long id);
}