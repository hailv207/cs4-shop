package com.red.services.cart;

import com.red.model.Cart;
import com.red.repository.cart.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    public CartServiceImpl() {
    }

    @Override
    public Iterable<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public void save(Cart entity) {
        cartRepository.save(entity);
    }

    @Override
    public void update(Cart entity) {
        cartRepository.save(entity);
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void delete(Cart entity) {
        cartRepository.delete(entity);
    }
}
