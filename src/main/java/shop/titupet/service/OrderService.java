package shop.titupet.service;

import shop.titupet.models.dtos.order.CreateOrderReq;
import shop.titupet.models.entities.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order getOrderById(Long id);

    Order createOrder(CreateOrderReq order);

    void deleteOrder(Long id);

    Order updateOrder(Order order);

}
