package com.red.repository.cart_item;

import com.red.model.Cart;
import com.red.model.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    Iterable<CartItem> findAllByCart(Cart cart);
}
