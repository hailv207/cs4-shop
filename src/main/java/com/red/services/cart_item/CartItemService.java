package com.red.services.cart_item;

import com.red.model.Cart;
import com.red.model.CartItem;
import com.red.services.IGeneralService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CartItemService extends IGeneralService<CartItem> {
    Iterable<CartItem> findAllByCart(Cart cart);
}
