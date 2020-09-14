package com.red.app.http.controller;

import com.red.cart.Cart;
import com.red.cart.CartManager;
import com.red.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    @Autowired
    private CartManager cartManager;

    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String doGet(Model model){
        Cart cart = cartManager.getCart();
        model.addAttribute("cart", cart);
        return null;
    }

    @PostMapping("/cart/add")
    public String doPostAdd(@RequestParam Long id, @RequestParam(required = false) int quantity){
        Cart cart = cartManager.getCart();

        return null;
    }

    @PostMapping("/cart/delete")
    public String doPostDelete(@RequestParam Long id){
        cartManager.remove(id);
        return null;
    }
}
