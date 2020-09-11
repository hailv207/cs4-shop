package com.red.app.http.controller.product;

import com.red.model.Category;
import com.red.model.Product;
import com.red.repository.users.CategoryRepository;
import com.red.services.product.ProductService;
import com.red.system.filesystem.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProductManagerController {
    @Autowired
    private Storage storage;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;

    @ModelAttribute("category")
    public Iterable<Category> categories() {
        return categoryRepository.findAll();
    }


    @GetMapping("/admin")
    public ModelAndView listProduct() {
        ModelAndView modelAndView = new ModelAndView("/productManager/product-manager");
        return modelAndView;
    }

    @GetMapping("/admin/product-manager")
    public ModelAndView showProductManager() {
        Iterable<Product> product = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("/admin/products-manager");
        modelAndView.addObject("products", product);
        return modelAndView;
    }

    @GetMapping("/admin/create-product")
    public ModelAndView showCreateProduct() {
        ModelAndView modelAndView = new ModelAndView("/admin/create-product");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/admin/create-product")
    public ModelAndView createProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/admin/create-product");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "New product created successfully");
        return modelAndView;
    }

    @GetMapping("/admin/edit-product/{id}")
    public ModelAndView showEditProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/admin/edit-product");
            modelAndView.addObject("product", product);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error/404");
            return modelAndView;
        }
    }

    @PostMapping("/admin/edit-product")
    public ModelAndView editProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/admin/edit-product");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "Product updated successfully");
        return modelAndView;
    }

    @GetMapping("/admin/delete-product/{id}")
    public ModelAndView showDeleteProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/admin/delete-product");
            modelAndView.addObject("product", product);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error/404");
            return modelAndView;
        }
    }

    @PostMapping("/admin/delete-product")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        productService.delete(product);
        return "redirect:/admin/product-manager";
    }

}
