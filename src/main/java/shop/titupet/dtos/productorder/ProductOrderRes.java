package shop.titupet.dtos.productorder;

import shop.titupet.dtos.product.ProductRes;
import shop.titupet.models.enums.ObjectStatus;

import java.time.LocalDateTime;

public record ProductOrderRes(
        Long id,
        int quantity,
        LocalDateTime createdAt,
        Long createdBy,
        LocalDateTime updatedAt,
        Long updatedBy,
        ObjectStatus objectStatus,
        ProductRes product

){
}
