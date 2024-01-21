package shop.titupet.service;

import shop.titupet.models.dtos.promotion.CreatePromotionDtoReq;
import shop.titupet.models.dtos.promotion.PromotionRes;
import shop.titupet.models.entities.Promotion;

import java.util.List;

public interface PromotionService {
    List<Promotion> getAllPromotion();

    Promotion getPromotionById(Long id);

    Promotion createPromotion(CreatePromotionDtoReq req);

    void deletePromotion(Long id);
}
