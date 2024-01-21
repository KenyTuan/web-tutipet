package shop.titupet.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.models.converter.OrderDtoConverter;
import shop.titupet.models.dtos.order.CreateOrderDtoReq;
import shop.titupet.models.dtos.order.OrderDtoRes;
import shop.titupet.models.entities.Order;
import shop.titupet.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiEndpoints.PREFIX)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(ApiEndpoints.ORDER_V1)
    public List<OrderDtoRes> getAllOrders(){
        return orderService.getAllOrders().stream()
                .map(OrderDtoConverter::toResponse).collect(Collectors.toList());
    }

    @PostMapping(ApiEndpoints.ORDER_V1)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDtoRes createOrder(@RequestBody CreateOrderDtoReq req){
        return OrderDtoConverter.toResponse(orderService.createOrder(req));
    }


}
