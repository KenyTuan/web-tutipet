package shop.titupet.models.dtos.promotion;

import shop.titupet.models.enums.DiscountType;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.models.enums.ObjectStatus;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record PromotionRes(
        Long id,
        String code,
        ZonedDateTime fromTime,
        ZonedDateTime toTime,
        EnableStatus enableStatus,
        DiscountType discountType,
        BigDecimal value,
        ZonedDateTime createdAt,
        Long createdBy,
        ZonedDateTime updatedAt,
        Long updatedBy,
        ObjectStatus objectStatus
) {
}
