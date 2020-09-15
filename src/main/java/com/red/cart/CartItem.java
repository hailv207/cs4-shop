package com.red.cart;

import com.red.model.Product;

public class CartItem {
    private Product product;
    private Integer quantity;

    public CartItem(){
    }

    public CartItem(Product product){
        this.product  = product;
        this.quantity = 1;
    }

    public Integer plus(){
        this.quantity = ++this.quantity;
        if (this.quantity > product.getQuantity()){
            this.quantity = product.getQuantity();
        }
        return this.quantity;
    }

    public Integer minus(){
        this.quantity = --this.quantity;
        if (this.quantity < 1){
            this.quantity = 1;
        }
        return this.quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getPrice(){
        return getQuantity() * product.getPrice();
    }
}
