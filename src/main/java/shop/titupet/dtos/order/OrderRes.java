package shop.titupet.dtos.order;

import shop.titupet.dtos.productorder.ProductOrderRes;
import shop.titupet.dtos.promotion.PromotionRes;
import shop.titupet.models.entities.Address;
import shop.titupet.models.entities.Promotion;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.models.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Set;

public record OrderRes(
        Long id,
        String note,
        OrderStatus status,
        LocalDateTime createdAt,
        Long createdBy,
        LocalDateTime updatedAt,
        Long updatedBy,
        ObjectStatus objectStatus,
        Address address,
        Set<ProductOrderRes> productOrderRes,
        Set<PromotionRes> promotion

){
}
