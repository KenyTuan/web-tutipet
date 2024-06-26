package shop.titupet.converter;

import shop.titupet.dtos.product.CreateProductReq;
import shop.titupet.dtos.product.ProductRes;
import shop.titupet.dtos.product.UpdateProductReq;
import shop.titupet.models.entities.Product;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.utils.PromotionUtils;

public class ProductDtoConverter {

    public static Product toEntity(CreateProductReq req){
        Product product = Product.builder()
                .name(req.getName())
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
