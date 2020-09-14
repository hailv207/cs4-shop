package com.red.cart;

import com.red.model.Product;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Long, CartItem> cart;
    public Cart(){
        cart = new HashMap<>();
    }

    public void addProduct(Product product){
        CartItem item = cart.get(product.getId());
        if (item == null){
            cart.put(product.getId(), new CartItem(product));
        }
    }

    public void addProduct(Product product, int quantity){
        CartItem item = cart.get(product.getId());
        if (item == null){
            item = new CartItem(product);
            item.setQuantity(quantity);
            cart.put(product.getId(), item);
        } else {
            item.setQuantity(quantity);
        }
    }

    public void remove(Long id){
        cart.remove(id);
    }

    public boolean check(Product product){
        CartItem item = cart.get(product.getId());
        return item != null;
    }

    public Map<Long, CartItem> getCart() {
        return cart;
    }

    public void setCart(Map<Long, CartItem> cart) {
        this.cart = cart;
    }
}
