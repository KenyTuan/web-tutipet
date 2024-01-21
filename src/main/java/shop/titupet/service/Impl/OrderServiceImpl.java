package shop.titupet.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.titupet.config.exception.BadRequestException;
import shop.titupet.config.exception.NotFoundException;
import shop.titupet.models.converter.OrderDtoConverter;
import shop.titupet.models.converter.ProductOrderDtoConverter;
import shop.titupet.models.dtos.order.CreateOrderDtoReq;
import shop.titupet.models.entities.Address;
import shop.titupet.models.entities.Order;
import shop.titupet.models.entities.ProductOrder;
import shop.titupet.models.entities.Promotion;
import shop.titupet.repository.*;
import shop.titupet.service.OrderService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;

    private final ProductOrderRepo productOrderRepo;

    private final PromotionRepo promotionRepo;
    private final AddressRepo addressRepo;

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public Order getOrderById(Long id) {

        return orderRepo.findById(id).orElseThrow(()-> new NotFoundException("404","Not Found!"));
    }

    @Override
    @Transactional
    public Order createOrder(CreateOrderDtoReq req) {
        final Set<Promotion> promotions = new HashSet<>(
                promotionRepo.findAllById(req.getPromotionCodes())) ;
        final Address address  = addressRepo.getReferenceById(req.getAddress_id());''

        try {
            final Order order = OrderDtoConverter.toEntity(req);

            final Set<ProductOrder> productOrders = req.getProductOrderReqs()
                    .stream()
                    .map(productOrderReq -> {
                        ProductOrder item = ProductOrderDtoConverter.toEntity(productOrderReq,order);
                                item.setProduct(productRepo.getReferenceById(productOrderReq.getProductId()));
                                return item;
                    })
                    .collect(Collectors.toSet());

            order.setProductOrders(productOrders);
            order.setPromotions(promotions);
            order.setAddress(address);
            orderRepo.save(order);

            productOrderRepo.saveAll(productOrders);

            return order;
        }catch (Exception e){
            throw new BadRequestException("400","Error " + e);
        }

    }

    @Override
    public void deleteOrder(Long id) {
    }

    @Override
    public Order updateOrder(Order order) {
        return null;
    }
}
