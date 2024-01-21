package shop.titupet.models.converter;

import shop.titupet.models.dtos.product.CreateProductReq;
import shop.titupet.models.dtos.product.ProductRes;
import shop.titupet.models.dtos.product.UpdateProductReq;
import shop.titupet.models.entities.Product;
import shop.titupet.models.entities.ProductType;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.utils.PromotionUtils;

public class ProductDtoConverter {

    public static Product toEntity(CreateProductReq req){
        Product product = Product.builder()
                .name(req.getName())
                .petTypes(req.getPetTypes())
                .price(req.getPrice())
                .description(req.getDescription())
                .info(req.getInfo())
                .img(req.getImg())
                .build();
        product.setStatus(EnableStatus.ENABLED);
        product.setObjectStatus(ObjectStatus.ACTIVE);
        return product;
    }

    public static Product toEntity(UpdateProductReq req){
        Product product = Product.builder()
                .name(req.getName())
                .petTypes(req.getPetTypes())
                .price(req.getPrice())
                .description(req.getDescription())
                .info(req.getInfo())
                .img(req.getImg())
                .build();
        product.setStatus(EnableStatus.ENABLED);
        product.setObjectStatus(ObjectStatus.ACTIVE);
        return product;
    }

    public static ProductRes toResponse(Product product){
        return new ProductRes(
                product.getId(),
                product.getName(),
                product.getPetTypes(),
                product.getPrice(),
                product.getDescription(),
                product.getInfo(),
                product.getImg(),
                product.getStatus(),
                product.getCreatedBy(),
                product.getCreatedAt(),
                product.getUpdatedBy(),
                product.getUpdatedAt(),
                product.getObjectStatus(),
                product.getType(),
                PromotionUtils.getCurrentPromotion(product.getPromotions())
        );
    }
}
