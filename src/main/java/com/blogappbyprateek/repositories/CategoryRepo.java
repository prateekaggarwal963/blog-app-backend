package com.blogappbyprateek.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogappbyprateek.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
