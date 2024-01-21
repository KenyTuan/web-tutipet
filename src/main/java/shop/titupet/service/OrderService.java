package shop.titupet.service;

import shop.titupet.models.dtos.order.CreateOrderDtoReq;
import shop.titupet.models.entities.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order getOrderById(Long id);

    Order createOrder(CreateOrderDtoReq order);

    void deleteOrder(Long id);

    Order updateOrder(Order order);

}
