package com.red.app.http.controller;

import com.red.model.Category;
import com.red.model.Product;
import com.red.services.category.CategoryService;
import com.red.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	private static final Integer size = 6;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
	public String index(@ModelAttribute("page") @RequestParam(required = false) Integer page, Model model){
		Pageable pageProduct = null;
		if (page == null || page < 1){
			pageProduct = PageRequest.of(0, size, Sort.by("id").descending());
		}else{
			--page;
			pageProduct = PageRequest.of(page, size, Sort.by("id").descending());
		}

		Iterable<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);

		Page<Product> products = productService.findAll(pageProduct);

		model.addAttribute("pages", products);

		return "layout/home";
	}
}
