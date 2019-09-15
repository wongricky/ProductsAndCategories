package com.rickywong.productsandcategories.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rickywong.productsandcategories.models.Category;
import com.rickywong.productsandcategories.models.Product;
import com.rickywong.productsandcategories.services.MainService;

@Controller
public class MainController {
	private final MainService mainService;
	
	public MainController(MainService mainService) {
		this.mainService = mainService;
	}
	
	@RequestMapping("/")
	public String home() {
		return "index.jsp";
	}
	
	@RequestMapping("/products/new")
	public String createProduct(Model model, @ModelAttribute("product") Product product) {
		model.addAttribute("product", product);
		return "newproduct.jsp";
	}
	
	@RequestMapping("/categories/new")
	public String createCategory(Model model, @ModelAttribute("category") Category category) {
		model.addAttribute("category", category);
		return "newcategory.jsp";
	}
	
	@RequestMapping("/products/new/process")
	public String createProductProcess(@Valid @ModelAttribute("product") Product product,
			BindingResult result) {
		if (result.hasErrors()) {
			return "newproduct.jsp";
		}
		else {
			mainService.createProduct(product);
			Long id = product.getId();
			return "redirect:/products/" + id;
		}
	}
	
	@RequestMapping("/categories/new/process")
	public String createCategoryProcess(@Valid @ModelAttribute("category") Category category,
			BindingResult result) {
		if (result.hasErrors()) {
			return "newcategory.jsp";
		}
		else {
			mainService.createCategory(category);
			Long id = category.getId();
			return "redirect:/categories/" + id;
		}
	}
	
	@RequestMapping("/products/{id}")
	public String showProduct (@PathVariable("id") Long id, Model model) {
		Product product = mainService.findProduct(id);
		model.addAttribute("product", product);
		List<Category> added = product.getCategories();
		model.addAttribute("added", added);
		
		List<Category> categories = mainService.allCategories();
		List<Category> menu = new ArrayList<Category>();
		
		menu.addAll(categories);
		menu.removeAll(added);
		model.addAttribute("menu", menu);
		return "product.jsp";
	}
	
	@RequestMapping("/categories/{id}")
	public String showCategory (@PathVariable("id") Long id, Model model) {
		Category category = mainService.findCategory(id);
		model.addAttribute("category", category);
		List<Product> added = category.getProducts();
		model.addAttribute("added", added);
		
		List<Product> products = mainService.allProducts();
		List<Product> menu = new ArrayList<Product>();
		
		menu.addAll(products);
		menu.removeAll(added);
		model.addAttribute("menu", menu);
		return "category.jsp";
	}
	
	@RequestMapping("/products/{id}/add")
	public String addCategoryToProduct (@PathVariable("id") Long id, HttpServletRequest request) {
		Product product = mainService.findProduct(id);
		String c = request.getParameter("name");
		Category category = mainService.findCategoryName(c);
		product.getCategories().add(category);
		mainService.createProduct(product);
		return "redirect:/products/" + id;
	}
	
	@RequestMapping("/categories/{id}/add")
	public String addProductToCategory (@PathVariable("id") Long id, HttpServletRequest request) {
		Category category = mainService.findCategory(id);
		String p = request.getParameter("name");
		Product product = mainService.findProductName(p);
		category.getProducts().add(product);
		mainService.createCategory(category);
		return "redirect:/categories/" + id;
	}
}