package shop.titupet.dtos.cart;

import shop.titupet.dtos.productCart.ProductCartRes;

import java.time.LocalDateTime;
import java.util.Set;

public record CartRes(
        Long id,
        Set<ProductCartRes> productCartRes,
        LocalDateTime createdAt,
        Long createdBy,
        LocalDateTime updatedAt,
        Long updatedBy

) {

}
