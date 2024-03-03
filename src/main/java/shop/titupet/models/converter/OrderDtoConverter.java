package shop.titupet.models.converter;

import shop.titupet.models.dtos.order.CreateOrderReq;
import shop.titupet.models.dtos.order.OrderRes;
import shop.titupet.models.dtos.order.UpdateOrderReq;
import shop.titupet.models.entities.Order;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.models.enums.OrderStatus;

import java.util.stream.Collectors;

public class OrderDtoConverter {

    public static Order toEntity(CreateOrderReq req){
        Order order = Order.builder()
                .note(req.getNote())
                .status(OrderStatus.OPEN)
                .build();

        order.setObjectStatus(ObjectStatus.ACTIVE);

        return order;
    }

    public static Order toEntity(UpdateOrderReq req){

        return Order.builder()
                .status(req.getStatus())
                .build();
    }

    public static OrderRes toResponse(Order order){
        return new OrderRes(
                order.getId(),
                order.getNote(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getCreatedBy(),
                order.getUpdatedAt(),
                order.getUpdatedBy(),
                order.getObjectStatus(),
                order.getAddress(),
                order.getProductOrders().stream().map(ProductOrderDtoConverter::toResponse).collect(Collectors.toSet())
//                PromotionUtils.getCurrentListPromotion(order.getPromotions())
        );
    }

}
