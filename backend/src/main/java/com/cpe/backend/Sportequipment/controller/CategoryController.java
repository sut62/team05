package com.cpe.backend.Sportequipment.controller;
import com.cpe.backend.Sportequipment.entity.Category;
import com.cpe.backend.Sportequipment.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class CategoryController {
    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/category")
    public Collection<Category> Category() {
        return categoryRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/category/{id}")
    public Optional<Category> Category(@PathVariable Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category;
    }

}