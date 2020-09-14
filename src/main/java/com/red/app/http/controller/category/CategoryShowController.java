package com.red.app.http.controller.category;

import com.red.model.Category;
import com.red.model.Product;
import com.red.services.category.CategoryService;
import com.red.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/category/{id}")
public class CategoryShowController {
    private static final Integer size = 6;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String doGet(@PathVariable("id") Long id, @ModelAttribute("page") @RequestParam(required = false) Integer page, Model model){
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (optionalCategory.isPresent()){
            Pageable pageProduct = null;
            if (page == null || page < 1){
                pageProduct = PageRequest.of(0, size, Sort.by("id").descending());
            }else{
                --page;
                pageProduct = PageRequest.of(page, size, Sort.by("id").descending());
            }

            Category category = optionalCategory.get();

            Page<Product> products = productService.findAllByCategory(category, pageProduct);

            model.addAttribute("category", category);
            model.addAttribute("pages", products);

            return "layout/category/index";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
