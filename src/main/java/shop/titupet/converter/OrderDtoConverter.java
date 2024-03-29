package shop.titupet.converter;

import shop.titupet.dtos.order.CreateOrderReq;
import shop.titupet.dtos.order.OrderRes;
import shop.titupet.dtos.order.UpdateOrderReq;
import shop.titupet.models.entities.Address;
import shop.titupet.models.entities.Order;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.models.enums.OrderStatus;
import shop.titupet.utils.PromotionUtils;

import java.util.stream.Collectors;

public class OrderDtoConverter {

    public static Order toEntity(CreateOrderReq req){
        Order order = Order.builder()
                .address(Address.builder().id(req.getAddress_id()).build())
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
                order.getProductOrders().stream().map(ProductOrderDtoConverter::toResponse).collect(Collectors.toSet()),
                order.getPromotions().stream().map(PromotionDtoConverter::toResponse).collect(Collectors.toSet())
        );
    }

}
