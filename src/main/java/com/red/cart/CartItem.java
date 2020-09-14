package com.red.cart;

import com.red.model.Product;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(){
    }

    public CartItem(Product product){
        this.product  = product;
        this.quantity = 1;
    }

    public void plus(){
        this.quantity++;
    }

    public void munus(){
        this.quantity--;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
