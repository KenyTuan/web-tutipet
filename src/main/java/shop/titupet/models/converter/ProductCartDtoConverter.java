package shop.titupet.models.converter;

import shop.titupet.models.dtos.cart.CartReq;
import shop.titupet.models.dtos.cart.CartRes;
import shop.titupet.models.dtos.productCart.ProductCartReq;
import shop.titupet.models.dtos.productCart.ProductCartRes;
import shop.titupet.models.entities.Cart;
import shop.titupet.models.entities.ProductCart;
import shop.titupet.models.enums.ObjectStatus;

public class ProductCartDtoConverter {

    public static ProductCartRes toResponse(ProductCart productCart){
        return new ProductCartRes(
                productCart.getId(),
                productCart.getQuantity(),
                ProductDtoConverter.toResponse(productCart.getProduct()),
                productCart.getCreatedAt(),
                productCart.getCreatedBy(),
                productCart.getUpdatedAt(),
                productCart.getUpdatedBy(),
                productCart.getObjectStatus()
        );
    }

    public static ProductCart toEntity(ProductCartReq req, Cart cart) {
        ProductCart productCart = ProductCart.builder()
                .quantity(req.getQuantity())
                .cart(cart)
                .build();
        productCart.setObjectStatus(ObjectStatus.ACTIVE);
        return productCart;
    }
}
