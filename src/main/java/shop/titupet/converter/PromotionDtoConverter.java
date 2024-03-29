package shop.titupet.converter;

import shop.titupet.dtos.promotion.CreatePromotionDtoReq;
import shop.titupet.dtos.promotion.PromotionRes;
import shop.titupet.dtos.promotion.UpdatePromotionReq;
import shop.titupet.models.entities.Promotion;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.models.enums.ObjectStatus;

public class PromotionDtoConverter {

    public static Promotion toEntity(CreatePromotionDtoReq req){
        Promotion promotion = Promotion.builder()
                .value(req.getValue())
                .enableStatus(EnableStatus.ENABLED)
                .discountType(req.getDiscountType())
                .fromTime(req.getFromTime())
                .toTime(req.getToTime())
                .build();
        promotion.setObjectStatus(ObjectStatus.ACTIVE);
        return promotion;
    }

    public static Promotion toEntity(UpdatePromotionReq req){
        Promotion promotion = Promotion.builder()
                .value(req.getValue())
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
                promotion.getFromTime(),
                promotion.getToTime(),
                promotion.getEnableStatus(),
                promotion.getDiscountType(),
                promotion.getValue(),
                promotion.getProducts(),
                promotion.getCreatedAt(),
                promotion.getCreatedBy(),
                promotion.getUpdatedAt(),
                promotion.getUpdatedBy(),
                promotion.getObjectStatus()
        );
    }

}
