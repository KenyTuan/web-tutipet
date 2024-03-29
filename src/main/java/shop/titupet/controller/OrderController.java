package shop.titupet.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.titupet.assembler.OrderModelAssembler;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.converter.OrderDtoConverter;
import shop.titupet.dtos.order.CreateOrderReq;
import shop.titupet.dtos.order.OrderRes;
import shop.titupet.dtos.order.UpdateOrderReq;
import shop.titupet.models.entities.Order;
import shop.titupet.service.OrderService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(ApiEndpoints.PREFIX)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderModelAssembler assembler;

    @GetMapping(ApiEndpoints.ORDER_V1)
    public CollectionModel<EntityModel<OrderRes>> getAllOrders(){

        final List<OrderRes> resList =orderService.getAllOrders()
                .stream()
                .map(OrderDtoConverter::toResponse)
                .toList();

        final List<EntityModel<OrderRes>> entityModels = resList
                .stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(entityModels,
                linkTo(methodOn(OrderController.class)
                        .getAllOrders())
                        .withSelfRel());
    }

    @GetMapping(value = ApiEndpoints.ORDER_V1 + "/{id}")
    public EntityModel<OrderRes> getOrderById(@PathVariable(name = "id") Long id){
        final Order order = orderService.getOrderById(id);
        return assembler.toModel(OrderDtoConverter.toResponse(order));
    }

    @PostMapping(ApiEndpoints.ORDER_V1)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderRes createOrder(@RequestBody @Valid CreateOrderReq req){

        return OrderDtoConverter.toResponse(
                        orderService.createOrder(req));
    }

//    @PutMapping(ApiEndpoints.ORDER_V1)
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<?> updateOrder(@RequestBody @Valid UpdateOrderReq req){
//        final EntityModel<OrderRes> entityModel = assembler
//                .toModel(OrderDtoConverter.toResponse(orderService.updateOrder(OrderDtoConverter.toEntity(req))));
//        return ResponseEntity
//                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
//                        .toUri())
//                .body(entityModel);
//    }

    @DeleteMapping(ApiEndpoints.ORDER_V1 + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("id") Long id){
        orderService.deleteOrder(id);
    }

    @PatchMapping(ApiEndpoints.ORDER_V1)
    @ResponseStatus(HttpStatus.OK)
    public OrderRes changeOpenOrder(@RequestBody @Valid UpdateOrderReq req){

        return OrderDtoConverter.toResponse(orderService.updateOrder(req));
    }

    @PatchMapping(ApiEndpoints.ORDER_V1 +"/change")
    @ResponseStatus(HttpStatus.OK)
    public OrderRes changePaidOrder(@RequestBody @Valid UpdateOrderReq req){

        return OrderDtoConverter.toResponse(orderService.changePaidOrder(req));
    }


}
