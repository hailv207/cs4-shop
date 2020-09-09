package com.red.model;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id",referencedColumnName = "id",nullable = false)
    private Cart cart;

    @OneToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",referencedColumnName = "id",nullable = false)
    private Product product;

    private int quantity;

    private long price;

    public CartItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
