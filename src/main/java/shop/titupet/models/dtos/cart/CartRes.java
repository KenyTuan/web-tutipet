package shop.titupet.models.dtos.cart;

import shop.titupet.models.dtos.productCart.ProductCartRes;
import shop.titupet.models.dtos.user.UserRes;
import shop.titupet.models.enums.ObjectStatus;

import java.time.ZonedDateTime;
import java.util.Set;

public record CartRes(
        Long id,
        Set<ProductCartRes> productCartRes,
        ZonedDateTime createdAt,
        Long createdBy,
        ZonedDateTime updatedAt,
        Long updatedBy

) {

}
