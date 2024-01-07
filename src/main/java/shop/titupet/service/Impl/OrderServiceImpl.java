package shop.titupet.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.titupet.models.entities.Order;
import shop.titupet.repository.OrderRepo;
import shop.titupet.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;


    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }


    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public Order getOrderById(Long id) {
        return null;
    }

    @Override
    public String createOrder(Order order) {
        return null;
    }

    @Override
    public String deleteOrder(Long id) {
        return null;
    }

    @Override
    public String updateOrder(Long id, Order order) {
        return null;
    }
}
