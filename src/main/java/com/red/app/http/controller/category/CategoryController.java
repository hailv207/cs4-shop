package com.red.app.http.controller.category;

import com.red.model.Category;
import com.red.model.Product;
import com.red.services.category.CategoryService;
import com.red.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/categories")
    public ModelAndView listCategory() {
        Iterable<Category> category = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("category/category-manager");
        modelAndView.addObject("categories", category);
        return modelAndView;
    }

    @GetMapping("/admin/create-category")
    public ModelAndView showCategory() {
        ModelAndView modelAndView = new ModelAndView("/category/create-category");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/admin/create-category")
    public String createCategory(@ModelAttribute("category") Category category,RedirectAttributes redirectAttributes) {
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("create_done", true);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/edit-category/{id}")
    public ModelAndView showEditCategory(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (category != null) {
            ModelAndView modelAndView = new ModelAndView("/category/edit-category");
            modelAndView.addObject("category", category);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error/404");
            return modelAndView;
        }
    }

    @PostMapping("/admin/edit-category")
    public String editCategory(@ModelAttribute("category") Category category,RedirectAttributes redirectAttributes) {
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("edit_done", true);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/delete-category/{id}")
    public ModelAndView showDelete(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (category != null) {
            ModelAndView modelAndView = new ModelAndView("/category/delete-category");
            modelAndView.addObject("category", category);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error/404");
            return modelAndView;
        }
    }

    @PostMapping("/admin/delete-category")
    public String deleteCategory(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
//        productService.deleteAllByCategory(category);
        List<Product> products = (List<Product>) productService.findAllByCategory(category);
        for (int i = 0; i < products.size(); i++) {
            products.get(i).setCategory(null);
            productService.save(products.get(i));
        }
        categoryService.delete(category);
        redirectAttributes.addFlashAttribute("delete_done", true);
        return "redirect:/admin/categories";
    }
}
