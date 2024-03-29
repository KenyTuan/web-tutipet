package shop.titupet.dtos.promotion;

import shop.titupet.dtos.product.ProductRes;
import shop.titupet.models.entities.Product;
import shop.titupet.models.enums.DiscountType;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.models.enums.ObjectStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Set;

public record PromotionRes(
        Long id,
        ZonedDateTime fromTime,
        ZonedDateTime toTime,
        EnableStatus enableStatus,
        DiscountType discountType,
        BigDecimal value,
        Set<Product> productRes,
        LocalDateTime createdAt,
        Long createdBy,
        LocalDateTime updatedAt,
        Long updatedBy,
        ObjectStatus objectStatus
) {
}
