package com.rickywong.productsandcategories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rickywong.productsandcategories.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository <Category, Long>{
	List<Category> findAll();
	Category findByName(String name);
}