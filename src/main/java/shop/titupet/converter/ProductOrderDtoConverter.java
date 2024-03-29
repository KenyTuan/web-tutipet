package shop.titupet.converter;

import shop.titupet.dtos.productorder.ProductOrderReq;
import shop.titupet.dtos.productorder.ProductOrderRes;
import shop.titupet.models.entities.ProductOrder;
import shop.titupet.models.enums.ObjectStatus;

public class ProductOrderDtoConverter {

    public static ProductOrder toEntity(ProductOrderReq req){
        ProductOrder productOrder = ProductOrder.builder()
                .quantity(req.getQuantity())
                .build();

        productOrder.setObjectStatus(ObjectStatus.ACTIVE);

        return productOrder;
    }



    public static ProductOrderRes toResponse(ProductOrder productOrder){
        return new ProductOrderRes(
                productOrder.getId(),
                productOrder.getQuantity(),
                productOrder.getCreatedAt(),
                productOrder.getCreatedBy(),
                productOrder.getUpdatedAt(),
                productOrder.getUpdatedBy(),
                productOrder.getObjectStatus(),
                ProductDtoConverter.toResponse(productOrder.getProduct())
        );
    }


}
