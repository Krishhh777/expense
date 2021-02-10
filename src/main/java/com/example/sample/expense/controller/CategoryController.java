package com.example.sample.expense.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.sample.expense.model.Category;
import com.example.sample.expense.repository.CategoryRepository;


@RestController
@RequestMapping("/api")
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;

	
	@GetMapping("/categories")
	Collection<Category> getDetails() {

		return categoryRepository.findAll();	}
	
	//Category/2
	@GetMapping("/category/{id}")
	ResponseEntity<?> getCategory(@PathVariable Long id){
		Optional<Category> category = categoryRepository.findById(id);
		return category.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/add")
	ResponseEntity<Category> addCategory(@Validated @RequestBody Category category ) throws URISyntaxException{
		Category result = categoryRepository.save(category);
		return ResponseEntity.created(new URI("/api/add"+ result.getId())).body(result);
		
		
	}
	
	@PutMapping("/update/{id}")
	ResponseEntity<Category> updateCategory(@Validated @RequestBody Category category ) throws URISyntaxException{
		Category result = categoryRepository.save(category);
		return ResponseEntity.ok().body(result);
		
	}
	

	@DeleteMapping("/delete/{id}")
	ResponseEntity<?> deleteCategory(@PathVariable Long id) {
		
		categoryRepository.deleteById(id);
		return ResponseEntity.ok().build();
		
	}


}
