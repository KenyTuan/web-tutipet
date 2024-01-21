package shop.titupet.models.dtos.productCart;

import shop.titupet.models.dtos.product.ProductRes;
import shop.titupet.models.enums.ObjectStatus;

import java.time.ZonedDateTime;

public record ProductCartRes(
        Long id,
        int quantity,
        ProductRes productRes,
        ZonedDateTime createdAt,
        Long createdBy,
        ZonedDateTime updatedAt,
        Long updatedBy,
        ObjectStatus objectStatus) {
}
