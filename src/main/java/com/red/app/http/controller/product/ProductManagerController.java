package com.red.app.http.controller.product;

import com.red.model.Product;
import com.red.services.product.ProductService;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductManagerController {
    @Autowired
    ProductService productService;

    @GetMapping({"/admin"})
    public ModelAndView showProductManager(){
        ModelAndView modelAndView = new ModelAndView("admin/products-manager");
        Iterable<Product> products = productService.findAll();
        modelAndView.addObject("products",products);
        return modelAndView;
    }

    @GetMapping({"/admin/{id}/edit"})
    public ModelAndView editProduct(@PathVariable("id")Long id){
        ModelAndView modelAndView = new ModelAndView("edit-product");
        Product product = productService.findById(id).get();
        if (product != null){
            modelAndView.addObject("product",product);
        }else{
            modelAndView.setViewName("error/404");
        }
        return modelAndView;
    }

    @GetMapping({"/admin/{id}/delete"})
    public  ModelAndView deleteProduct(@PathVariable("id")Long id){
        ModelAndView modelAndView = new ModelAndView("delete-product");
        Product product = productService.findById(id).get();
        if (product != null){
            modelAndView.addObject("product",product);
        }else{
            modelAndView.setViewName("error/404");
        }
        return modelAndView;
    }

}
