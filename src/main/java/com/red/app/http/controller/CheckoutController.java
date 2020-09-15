package com.red.app.http.controller;

import com.red.app.form.FormCheckout;
import com.red.cart.Cart;
import com.red.cart.CartItem;
import com.red.cart.CartManager;
import com.red.model.Voucher;
import com.red.services.cart.CartService;
import com.red.services.cart_item.CartItemService;
import com.red.services.voucher.VoucherService;
import com.red.system.auth.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class CheckoutController {
    @Autowired
    private CartManager cartManager;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private Auth auth;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/checkout")
    public String doGet(Model model){
        if (cartManager.size() < 1){
            return "redirect:/cart";
        }

        Cart cart = cartManager.getCart();
        model.addAttribute("cart", cart.getCart().values());
        model.addAttribute("formCheckout", new FormCheckout());

        return "layout/checkout/index";
    }

    @PostMapping("/checkout")
    public String doPost(@Valid @ModelAttribute("formCheckout") FormCheckout formCheckout, BindingResult bindingResult, Model model){
        if (cartManager.size() < 1){
            return "redirect:/cart";
        }

        Cart cart = cartManager.getCart();
        model.addAttribute("cart", cart.getCart().values());

        Voucher voucher = null;

        if (formCheckout.getVoucher() != null){
            Optional<Voucher> optionalVoucher = voucherService.findByCode(formCheckout.getVoucher());
            if (optionalVoucher.isPresent()){
                // Mã đúng
                voucher = optionalVoucher.get();
            }else{
                // Mã sai
                bindingResult.rejectValue("voucher", "voucher.not_found", "Voucher not exists");

                return "layout/checkout/index";
            }
        }

        // Tạo giỏ hàng
        com.red.model.Cart cartModel = new com.red.model.Cart();

        cartModel.setAddress(formCheckout.getAddress());
        cartModel.setCustomerName(formCheckout.getFullname());
        cartModel.setPhone(formCheckout.getPhone());

        if (auth.check()){
            cartModel.setUser(auth.user());
        }

        if (voucher != null){
            cartModel.setVoucher(voucher);
        }

        // Lưu giỏ hàng vào CSDL
        cartService.save(cartModel);

        //
        for (CartItem cartItem: cart.getCart().values()){

            com.red.model.CartItem cartItemModel = new com.red.model.CartItem();
            cartItemModel.setCart(cartModel);
            cartItemModel.setPrice(cartItem.getProduct().getPrice());
            cartItemModel.setProduct(cartItem.getProduct());
            cartItemModel.setQuantity(cartItem.getQuantity());

            cartItemService.save(cartItemModel);

        }

        return "redirect:/cart/" + cartModel.getId();
    }

    @GetMapping("/cart/{id}")
    public String doGetCart(@PathVariable("id") Long id, Model model){
        Optional<com.red.model.Cart> optionalCart = cartService.findById(id);
        if (optionalCart.isPresent()){
            com.red.model.Cart cart = optionalCart.get();
            Iterable<com.red.model.CartItem> cartItems = cartItemService.findAllByCart(cart);

            Long price   = 0L;
            int quantity = 0;

            for (com.red.model.CartItem cartItem: cartItems){
                quantity += cartItem.getQuantity();
                price    += cartItem.getQuantity() * cartItem.getPrice();
            }

            model.addAttribute("cart",      cart);
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("quantity",  quantity);
            model.addAttribute("price",     price);

            return "layout/checkout/cart";
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
