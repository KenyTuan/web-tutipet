package shop.titupet.converter;

import shop.titupet.dtos.productType.ProductTypeRes;
import shop.titupet.models.entities.ProductType;

public class ProductTypeDtoConverter {

    public static ProductTypeRes toResponse(ProductType type){
        return new ProductTypeRes(
                type.getId(),
                type.getName(),
                type.getPetTypes(),
                type.getProducts(),
                type.getCreatedBy(),
                type.getCreatedAt(),
                type.getUpdatedBy(),
                type.getUpdatedAt(),
                type.getObjectStatus()
        );
    }

}
