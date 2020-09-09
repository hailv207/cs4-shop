package com.red.app.http.controller.product;

import com.red.model.Product;
import com.red.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/", "/home"})
    ModelAndView showAllProducts() {
        ModelAndView modelAndView = new ModelAndView("home");
        Iterable<Product> products = productService.findAll();
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
