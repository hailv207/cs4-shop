package com.red.app.http.controller.product;

import com.red.model.Image;
import com.red.model.Product;
import com.red.services.image.ImageService;
import com.red.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product/{id}")
public class ProductDetailController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageService imageService;

    @GetMapping
    public String doGet(@PathVariable("id") Long id, Model model, HttpServletResponse response){
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()){
            Product product            = optionalProduct.get();
            Iterable<Image> images     = imageService.findAllByProduct(product);
            List<Product> sameProducts = productService.findRandomByCategory(product.getCategory(), product, 10);

            model.addAttribute("images", images);
            model.addAttribute("product", product);
            model.addAttribute("same_products", sameProducts);

            return "layout/product/product-detail";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
