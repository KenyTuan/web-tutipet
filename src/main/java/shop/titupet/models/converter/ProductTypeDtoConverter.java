package shop.titupet.models.converter;

import shop.titupet.models.dtos.product.ProductRes;
import shop.titupet.models.dtos.productType.ProductTypeRes;
import shop.titupet.models.entities.Product;
import shop.titupet.models.entities.ProductType;

public class ProductTypeDtoConverter {

    public static ProductTypeRes toResponse(ProductType type){
        return new ProductTypeRes(
                type.getId(),
                type.getName(),
                type.getProducts(),
                type.getCreatedBy(),
                type.getCreatedAt(),
                type.getUpdatedBy(),
                type.getUpdatedAt(),
                type.getObjectStatus()
        );
    }

}
