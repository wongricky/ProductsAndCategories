package com.rickywong.productsandcategories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rickywong.productsandcategories.models.Category;
import com.rickywong.productsandcategories.models.CategoryProduct;
import com.rickywong.productsandcategories.models.Product;
import com.rickywong.productsandcategories.repositories.CategoryProductRepository;
import com.rickywong.productsandcategories.repositories.CategoryRepository;
import com.rickywong.productsandcategories.repositories.ProductRepository;

@Service
public class MainService {
	private final CategoryRepository catRepo;
	private final CategoryProductRepository prodCatRepo;
	private final ProductRepository prodRepo;
	
	public MainService (CategoryRepository catRepo,
			CategoryProductRepository prodCatRepo, ProductRepository prodRepo) {
		this.catRepo = catRepo;
		this.prodCatRepo = prodCatRepo;
		this.prodRepo = prodRepo;
	}
	
	public List<Product> allProducts() {
		return prodRepo.findAll();
	}
	
	public List<Category> allCategories() {
		return catRepo.findAll();
	}
	
	public List<CategoryProduct> allProductCategories() {
		return prodCatRepo.findAll();
	}
	
	public Product createProduct(Product product) {
		return prodRepo.save(product);
	}
	
	public Category createCategory(Category category) {
		return catRepo.save(category);
	}
	
	public Product findProduct(Long id) {
		Optional<Product> optionalProduct = prodRepo.findById(id);
        if(optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        else {
            return null;
        }
	}
	
	public Category findCategory(Long id) {
		Optional<Category> optionalCategory = catRepo.findById(id);
        if(optionalCategory.isPresent()) {
            return optionalCategory.get();
        }
        else {
            return null;
        }
	}
	
	public Category findCategoryName(String name) {
		return catRepo.findByName(name);
	}
	
	public Product findProductName(String name) {
		return prodRepo.findByName(name);
	}
} 
