package shop.titupet.models.converter;

import shop.titupet.models.dtos.cart.CartReq;
import shop.titupet.models.dtos.cart.CartRes;
import shop.titupet.models.entities.Cart;
import shop.titupet.models.enums.ObjectStatus;

import java.util.stream.Collectors;

public class CartDtoConverter {

    public static CartRes toResponse(Cart cart){
        return new CartRes(cart.getId(),
                UserDtoConverter.toResponse(cart.getUser()),
                cart.getProductCarts().stream().map(ProductCartDtoConverter::toResponse).collect(Collectors.toSet()),
                cart.getCreatedAt(),
                cart.getCreatedBy(),
                cart.getUpdatedAt(),
                cart.getUpdatedBy(),
                cart.getObjectStatus()
        );
    }

    public static Cart toEntity(CartReq req) {
        Cart cart = new Cart();
        cart.setObjectStatus(ObjectStatus.ACTIVE);
        return cart;
    }
}
