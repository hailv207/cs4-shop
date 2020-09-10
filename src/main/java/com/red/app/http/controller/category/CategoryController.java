package com.red.app.http.controller.category;

import com.red.model.Category;
import com.red.model.Product;
import com.red.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/categories")
    public ModelAndView listCategory(){
       Iterable<Category> category = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("category/category-manager");
        modelAndView.addObject("categories", category);
        return modelAndView;
    }

    @GetMapping("/admin/create-category")
    public ModelAndView showCategory(){
        ModelAndView modelAndView = new ModelAndView("/category/create-category");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/admin/create-category")
    public ModelAndView createCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/create-category");
        modelAndView.addObject("category", new Product());
        return modelAndView;
    }

    @GetMapping("/admin/edit-category/{id}")
    public ModelAndView showEditCategory(@PathVariable Long id){
      Optional<Category> category = categoryService.findById(id);
      if (category != null){
          ModelAndView modelAndView = new ModelAndView("/category/edit-category");
          modelAndView.addObject("category", category);
          return modelAndView;
      } else {
          ModelAndView modelAndView = new ModelAndView("/error/404");
          return modelAndView;
      }
    }

    @PostMapping("/admin/edit-category")
    public ModelAndView editCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/edit-category");
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    @GetMapping("/admin/delete-category/{id}")
    public ModelAndView showDelete(@PathVariable Long id){
       Optional<Category> category = categoryService.findById(id);
       if (category != null){
           ModelAndView modelAndView = new ModelAndView("/category/delete-category");
           modelAndView.addObject("category", category);
           return modelAndView;
       }else {
           ModelAndView modelAndView = new ModelAndView("/error/404");
           return modelAndView;
       }
    }

    @GetMapping("/admin/delete-category")
    public String deleteCategory(@ModelAttribute("category") Category category){
        categoryService.delete(category);
        return "redirect:/admin/categories";
    }
}
