package shop.titupet.dtos.productCart;

import shop.titupet.dtos.product.ProductRes;
import shop.titupet.models.enums.ObjectStatus;

import java.time.LocalDateTime;

public record ProductCartRes(
        Long id,
        int quantity,
        ProductRes productRes,
        LocalDateTime createdAt,
        Long createdBy,
        LocalDateTime updatedAt,
        Long updatedBy,
        ObjectStatus objectStatus) {
}
