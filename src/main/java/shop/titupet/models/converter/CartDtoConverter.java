package shop.titupet.models.converter;

import shop.titupet.models.dtos.cart.CreateCartReq;
import shop.titupet.models.dtos.cart.CartRes;
import shop.titupet.models.dtos.cart.UpdateCartReq;
import shop.titupet.models.entities.Cart;
import shop.titupet.models.enums.ObjectStatus;

import java.util.stream.Collectors;

public class CartDtoConverter {

    public static CartRes toResponse(Cart cart){
        return new CartRes(
                cart.getId(),
                cart.getProductCarts().stream().map(ProductCartDtoConverter::toResponse).collect(Collectors.toSet()),
                cart.getCreatedAt(),
                cart.getCreatedBy(),
                cart.getUpdatedAt(),
                cart.getUpdatedBy()
        );
    }

    public static Cart toEntity(CreateCartReq req) {
        Cart cart = new Cart();
        cart.setObjectStatus(ObjectStatus.ACTIVE);
        return cart;
    }

    public static Cart toEntity(UpdateCartReq req) {
        return Cart.builder()
                .id(req.getId())
                .productCarts(
                        req.getProductCartReqs().stream()
                                .map(ProductCartDtoConverter::toEntity)
                                .collect(Collectors.toSet()))
                .build();
    }
}
