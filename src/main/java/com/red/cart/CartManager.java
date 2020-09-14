package com.red.cart;

import com.red.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class CartManager {
    @Autowired
    private HttpSession session;

    public CartManager(){
    }

    public Cart getCart(){
        Cart cart = null;
        if (session.getAttribute("cart") == null){
            cart = new Cart();
            session.setAttribute("cart", cart);
        }else{
            cart = (Cart) session.getAttribute("cart");
        }
        return cart;
    }

    public void addProduct(Product product){
        Cart cart = getCart();
        cart.addProduct(product);
    }

    public void addProduct(Product product, int quantity){
        Cart cart = getCart();
        cart.addProduct(product, quantity);
    }

    public void remove(Long id){
        Cart cart = getCart();
        cart.remove(id);
    }

    public boolean check(Product product){
        Cart cart = getCart();
        return cart.check(product);
    }
}
