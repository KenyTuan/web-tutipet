package shop.titupet.models.dtos.productorder;

import shop.titupet.models.dtos.product.ProductRes;
import shop.titupet.models.entities.Product;
import shop.titupet.models.enums.ObjectStatus;

import java.time.ZonedDateTime;

public record ProductOrderRes(
        Long id,
        int quantity,
        ZonedDateTime createdAt,
        Long createdBy,
        ZonedDateTime updatedAt,
        Long updatedBy,
        ObjectStatus objectStatus,
        ProductRes product

){
}
