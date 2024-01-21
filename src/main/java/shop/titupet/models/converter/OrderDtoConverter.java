package shop.titupet.models.converter;

import shop.titupet.models.dtos.order.CreateOrderDtoReq;
import shop.titupet.models.dtos.order.OrderDtoRes;
import shop.titupet.models.dtos.order.UpdateOrderDtoReq;
import shop.titupet.models.entities.Order;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.models.enums.OrderStatus;
import shop.titupet.utils.PromotionUtils;

import java.util.stream.Collectors;

public class OrderDtoConverter {

    public static Order toEntity(CreateOrderDtoReq req){
        Order order = Order.builder()
                .note(req.getNote())
                .status(OrderStatus.OPEN)
                .build();

        order.setObjectStatus(ObjectStatus.ACTIVE);

        return order;
    }

    public static Order toEntity(UpdateOrderDtoReq req){

        return Order.builder()
                .status(req.getStatus())
                .build();
    }

    public static OrderDtoRes toResponse(Order order){
        return new OrderDtoRes(
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
                PromotionUtils.getCurrentListPromotion(order.getPromotions())
        );
    }

}
