package shop.titupet.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.titupet.dtos.order.UpdateOrderReq;
import shop.titupet.exception.BadRequestException;
import shop.titupet.exception.NotFoundException;
import shop.titupet.converter.OrderDtoConverter;
import shop.titupet.converter.ProductOrderDtoConverter;
import shop.titupet.dtos.order.CreateOrderReq;
import shop.titupet.dtos.productorder.ProductOrderReq;
import shop.titupet.models.entities.*;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.models.enums.OrderStatus;
import shop.titupet.repository.*;
import shop.titupet.security.JwtService;
import shop.titupet.service.OrderService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;
    private final ProductOrderRepo productOrderRepo;
    private final ProductCartRepo productCartRepo;
    private final PromotionRepo promotionRepo;

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public Order getOrderById(Long id) {

        return orderRepo.findById(id).orElseThrow(()-> new NotFoundException("404","Not Found!"));

    }

    @Override
    public Order createOrder(CreateOrderReq req) {
        try {
            final Order order = OrderDtoConverter.toEntity(req);

            final Set<ProductCart> productCarts = req.getProductOrderReqs()
                    .stream()
                    .map(this::getProductCart)
                    .collect(Collectors.toSet());

            final Set<ProductOrder> productOrders = req.getProductOrderReqs()
                    .stream()
                    .map(productOrderReq -> {
                        ProductOrder item = ProductOrderDtoConverter.toEntity(productOrderReq);
                        item.setProduct(productRepo.getReferenceById(productOrderReq.getProductId()));
                        item.setOrder(order);
                        return item;
                    })
                    .collect(Collectors.toSet());

            final Set<Promotion> promotions = promotionRepo.findAllInId(req.getPromotionCodes());

            for (Promotion promotion : promotions) {
                promotion.getOrders().add(order);
            }
            order.setProductOrders(productOrders);
            order.setPromotions(promotions);

            orderRepo.save(order);
            productCartRepo.saveAll(productCarts);
            productOrderRepo.saveAll(productOrders);
            return order;
        }catch (Exception e){
            throw new BadRequestException("400","Error " + e);
        }
    }

    @Override
    public void deleteOrder(Long id) {
        try{
            final Order order = orderRepo.findById(id)
                    .orElseThrow(()-> new NotFoundException("404","Not found!"));

            order.setObjectStatus(ObjectStatus.DELETED);
            orderRepo.save(order);
        }catch (Exception e){
            throw new BadRequestException("400","Error Server " + e);
        }
    }

    @Override
    public Order updateOrder(UpdateOrderReq req) {
        try{
            final Order order = orderRepo.findById(req.getId())
                    .orElseThrow(()-> new NotFoundException("404","Not found!"));
            if (req.getStatus() == OrderStatus.SUBMITTED){
                order.setStatus(OrderStatus.SUBMITTED);
            } else if (req.getStatus() == OrderStatus.ON_DELIVERY) {
                order.setStatus(OrderStatus.ON_DELIVERY);
            }else {
                order.setStatus(OrderStatus.DONE);
            }

            orderRepo.save(order);

            return order;
        }catch (Exception e){
            throw new BadRequestException("400","Error Server " + e);
        }
    }

    @Override
    public Order changePaidOrder(UpdateOrderReq req) {
        try{
            final Order order = orderRepo.findById(req.getId())
                    .orElseThrow(()-> new NotFoundException("404","Not found!"));
            order.setStatus(OrderStatus.PAID);

            orderRepo.save(order);
            return order;
        }catch (Exception e){
            throw new BadRequestException("400","Error Server " + e);
        }
    }

    private ProductCart getProductCart(ProductOrderReq productOrderReq) {
        ProductCart item = productCartRepo.getReferenceById(productOrderReq.getProductCartId());
        item.setObjectStatus(ObjectStatus.DELETED);
        return item;
    }

}
