package shop.titupet.service.Impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import shop.titupet.dtos.promotion.UpdatePromotionReq;
import shop.titupet.exception.NotFoundException;
import shop.titupet.exception.TitupetException;
import shop.titupet.converter.PromotionDtoConverter;
import shop.titupet.dtos.promotion.CreatePromotionDtoReq;
import shop.titupet.models.entities.Product;
import shop.titupet.models.entities.Promotion;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.repository.ProductRepo;
import shop.titupet.repository.PromotionRepo;
import shop.titupet.service.PromotionService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class PromotionServiceImpl implements PromotionService {

    final PromotionRepo promotionRepo;

    final ProductRepo productRepo;

    @Override
    public List<Promotion> getAllPromotion() {
        return promotionRepo.findAllActive();
    }

    @Override
    public Promotion getPromotionById(Long id) {
        return promotionRepo.findActiveById(id)
                .orElseThrow(() -> new NotFoundException("404","Not Found"));
    }

    @Override
    public Promotion createPromotion(CreatePromotionDtoReq req) {
        Set<Product> product = new HashSet<>(
                productRepo.findAllById(req.getProductIds()));
        try {

            final Promotion promotion = PromotionDtoConverter.toEntity(req);

            promotion.setProducts(product);
            promotionRepo.save(promotion);

            return promotion;
        }catch (Exception e){
            throw new TitupetException("400","Error "+ e);
        }
    }

    @Override
    public void deletePromotion(Long id) {
        final Promotion promotion = promotionRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("404","Not Found"));
        try {
            promotion.setObjectStatus(ObjectStatus.DELETED);
            promotionRepo.save(promotion);
        }catch (Exception e){
            throw new TitupetException("400","Error "+ e);
        }
    }

    @Override
    public Promotion updatePromotion(UpdatePromotionReq req) {
        try {

            final Promotion promotion = promotionRepo.getReferenceById(req.getId());

            final Promotion newPromotion = PromotionDtoConverter.toEntity(req);
            Set<Product> product = new HashSet<>(
                productRepo.findAllById(req.getProductIds()));
            promotion.setObjectStatus(ObjectStatus.DELETED);

            newPromotion.setEnableStatus(promotion.getEnableStatus());
            newPromotion.setProducts(product);

            promotionRepo.save(promotion);
            promotionRepo.save(newPromotion);

            return newPromotion;
        }catch (Exception e){
            throw new TitupetException("400","Error "+ e);
        }
    }


}
