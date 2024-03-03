package shop.titupet.models.dtos.order;

import shop.titupet.models.dtos.productorder.ProductOrderRes;
import shop.titupet.models.entities.Address;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.models.enums.OrderStatus;

import java.time.ZonedDateTime;
import java.util.Set;

public record OrderRes(
        Long id,
        String note,
        OrderStatus status,
        ZonedDateTime createdAt,
        Long createdBy,
        ZonedDateTime updatedAt,
        Long updatedBy,
        ObjectStatus objectStatus,
        Address address,
        Set<ProductOrderRes> productOrderRes

){
}
