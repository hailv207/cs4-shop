package com.red.app.http.controller;

import com.red.app.response.cart.CartStatus;
import com.red.cart.Cart;
import com.red.cart.CartItem;
import com.red.cart.CartManager;
import com.red.model.Product;
import com.red.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;

@Controller
public class CartController {

    @Autowired
    private CartManager cartManager;

    @Autowired
    private ProductService productService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/cart")
    public String doGet(Model model){
        Cart cart = cartManager.getCart();
        model.addAttribute("cart", cart.getCart().values());
        return "layout/cart/index";
    }

    @PostMapping("/cart/add")
    @ResponseBody
    public ResponseEntity<CartStatus> doPostAdd(@RequestParam Long id, @RequestParam(required = false) Integer quantity, HttpServletRequest request){
        Locale locale = RequestContextUtils.getLocale(request);

        Cart cart = cartManager.getCart();

        Optional<Product> optionalProduct = productService.findById(id);

        CartStatus cartStatus = new CartStatus();

        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();

            if (cartManager.check(product)){
                cartStatus.setMessage(messageSource.getMessage("site.card.product_exist", new Object[]{}, locale));
            } else {
                cartStatus.setMessage(messageSource.getMessage("site.card.add_success", new Object[]{}, locale));
                if (quantity != null){
                    cart.addProduct(product, quantity);
                } else {
                    cart.addProduct(product);
                }
            }
            cartStatus.setStatus(200);
        }else{
            cartStatus.setStatus(400);
            cartStatus.setMessage(messageSource.getMessage("site.card.add_fail_product_not_found", new Object[]{}, locale));
        }
        cartStatus.setNumber(cartManager.size());

        return new ResponseEntity<CartStatus>(cartStatus, HttpStatus.OK);
    }

    @PostMapping("/cart/update")
    @ResponseBody
    public ResponseEntity<CartStatus> doPostUpdate(@RequestParam Long id, @RequestParam String type){
        Cart cart = cartManager.getCart();
        CartStatus cartStatus = new CartStatus();
        if (cartManager.check(id)){
            CartItem cartItem = cartManager.getItem(id);
            if (type.equals("plus")){
                cartStatus.setNumber(cartItem.plus());
            }else{
                cartStatus.setNumber(cartItem.minus());
            }
        }
        return new ResponseEntity<CartStatus>(cartStatus, HttpStatus.OK);
    }

    @PostMapping("/cart/delete")
    @ResponseBody
    public ResponseEntity<CartStatus> doPostDelete(@RequestParam Long id){
        cartManager.remove(id);
        CartStatus cartStatus = new CartStatus();
        cartStatus.setNumber(cartManager.size());
        return new ResponseEntity<CartStatus>(cartStatus, HttpStatus.OK);
    }
}
