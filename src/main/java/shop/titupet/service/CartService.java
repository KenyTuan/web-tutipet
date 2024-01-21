package shop.titupet.service;

import shop.titupet.models.dtos.cart.CartReq;
import shop.titupet.models.entities.Cart;

import java.util.List;

public interface CartService {


    List<Cart> getAllCart();

    Cart createCart(CartReq req);
}
