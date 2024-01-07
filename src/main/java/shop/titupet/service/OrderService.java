package shop.titupet.service;

import shop.titupet.models.entities.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order getOrderById(Long id);

    String createOrder(Order order);

    String deleteOrder(Long id);

    String updateOrder(Long id,Order order);

}
