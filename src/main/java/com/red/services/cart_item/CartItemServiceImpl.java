package com.red.services.cart_item;

import com.red.model.Cart;
import com.red.model.CartItem;
import com.red.repository.cart_item.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItemServiceImpl(){}

    @Override
    public Iterable<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public void save(CartItem entity) {
        cartItemRepository.save(entity);
    }

    @Override
    public void update(CartItem entity) {
        cartItemRepository.save(entity);
    }

    @Override
    public Optional<CartItem> findById(Long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public void delete(CartItem entity) {
        cartItemRepository.delete(entity);
    }

    @Override
    public Iterable<CartItem> findAllByCart(Cart cart) {
        return cartItemRepository.findAllByCart(cart);
    }
}
