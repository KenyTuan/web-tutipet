package shop.titupet.models.converter;

import shop.titupet.models.dtos.product.CreateProductReq;
import shop.titupet.models.dtos.product.ProductRes;
import shop.titupet.models.dtos.product.UpdateProductReq;
import shop.titupet.models.entities.Product;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.models.enums.ObjectStatus;

public class ProductDtoConverter {

    public static Product toEntity(CreateProductReq req){
        Product product = Product.builder()
                .name(req.getName())
                .price(req.getPrice())
                .description(req.getDescription())
                .info(req.getInfo())
                .img(req.getImg())
                .petTypes(req.getPetTypes())
                .status(EnableStatus.ENABLED)
                .build();
        product.setObjectStatus(ObjectStatus.ACTIVE);
        return product;
    }

    public static Product toEntity(UpdateProductReq req){
        Product product = Product.builder()
                .name(req.getName())
                .price(req.getPrice())
                .petTypes(req.getPetTypes())
                .description(req.getDescription())
                .info(req.getInfo())
                .img(req.getImg())
                .build();
        product.setObjectStatus(ObjectStatus.ACTIVE);
        return product;
    }

    public static ProductRes toResponse(Product product){
        return new ProductRes(
                product.getPetTypes(),
                product.getName(),
                product.getDescription(),
                product.getInfo(),
                product.getPrice(),
                product.getImg(),
                product.getStatus()
        );
    }
}
