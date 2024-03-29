package shop.titupet.converter;

import shop.titupet.dtos.productCart.ProductCartReq;
import shop.titupet.dtos.productCart.ProductCartRes;
import shop.titupet.dtos.productCart.UpdateProductCartReq;
import shop.titupet.models.entities.Cart;
import shop.titupet.models.entities.Product;
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
                .product(Product.builder().id(req.getProductId()).build())
                .quantity(1)
                .cart(cart)
                .build();
        productCart.setObjectStatus(ObjectStatus.ACTIVE);
        return productCart;
    }

    public static ProductCart toEntity(UpdateProductCartReq req) {
        ProductCart productCart = ProductCart.builder()
                .id(req.getId())
                .product(Product.builder().id(req.getProductId()).build())
                .quantity(req.getQuantity())
                .build();
        productCart.setObjectStatus(ObjectStatus.ACTIVE);
        return productCart;
    }
}
