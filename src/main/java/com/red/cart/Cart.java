package com.red.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class Cart {

    @Autowired
    private HttpSession session;

    public Cart(){
    }
}
