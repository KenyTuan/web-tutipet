package shop.titupet.service;

import jakarta.servlet.http.HttpServletRequest;
import shop.titupet.models.dtos.cart.CartRes;
import shop.titupet.models.dtos.cart.CreateCartReq;
import shop.titupet.models.dtos.cart.UpdateCartReq;
import shop.titupet.models.entities.Cart;

import java.util.Collection;
import java.util.List;

public interface CartService {


    List<Cart> getAllCart();

    Cart createCart(CreateCartReq req, String token);

    Cart getCartById(Long id);

    Cart getAllCartByUser(String token);

    void deleteCart(Long id);

    Cart updateCart(UpdateCartReq req);

    void deleteAllProductCart(String token);
}
