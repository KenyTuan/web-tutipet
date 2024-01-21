package shop.titupet.models.converter;

import shop.titupet.models.dtos.product.ProductRes;
import shop.titupet.models.dtos.promotion.CreatePromotionDtoReq;
import shop.titupet.models.dtos.promotion.PromotionRes;
import shop.titupet.models.entities.Product;
import shop.titupet.models.entities.Promotion;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.models.enums.ObjectStatus;

public class PromotionDtoConverter {

    public static Promotion toEntity(CreatePromotionDtoReq req){
        Promotion promotion = Promotion.builder()
                .code(req.getCode())
                .value(req.getValue())
                .enableStatus(EnableStatus.ENABLED)
                .discountType(req.getDiscountType())
                .fromTime(req.getFromTime())
                .toTime(req.getToTime())
                .build();
        promotion.setObjectStatus(ObjectStatus.ACTIVE);
        return promotion;
    }

    public static PromotionRes toResponse(Promotion promotion){
        return new PromotionRes(
                promotion.getId(),
                promotion.getCode(),
                promotion.getFromTime(),
                promotion.getToTime(),
                promotion.getEnableStatus(),
                promotion.getDiscountType(),
                promotion.getValue(),
                promotion.getCreatedAt(),
                promotion.getCreatedBy(),
                promotion.getUpdatedAt(),
                promotion.getUpdatedBy(),
                promotion.getObjectStatus()
        );
    }

}
