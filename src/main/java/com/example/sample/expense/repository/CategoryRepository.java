package com.example.sample.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sample.expense.model.Category;



@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	Category findByCategory(String category);

}
