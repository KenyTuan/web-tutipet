package shop.titupet.models.converter;

import shop.titupet.models.dtos.productorder.ProductOrderReq;
import shop.titupet.models.dtos.productorder.ProductOrderRes;
import shop.titupet.models.entities.Order;
import shop.titupet.models.entities.ProductOrder;
import shop.titupet.models.enums.ObjectStatus;

public class ProductOrderDtoConverter {

    public static ProductOrder toEntity(ProductOrderReq req, Order order){
        ProductOrder productOrder = ProductOrder.builder()
                .quantity(req.getQuantity())
                .order(order)
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
