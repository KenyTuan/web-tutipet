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
import shop.titupet.models.converter.OrderDtoConverter;
import shop.titupet.models.converter.ProductDtoConverter;
import shop.titupet.models.dtos.order.CreateOrderReq;
import shop.titupet.models.dtos.order.OrderRes;
import shop.titupet.models.dtos.order.UpdateOrderReq;
import shop.titupet.models.dtos.product.ProductRes;
import shop.titupet.models.entities.Order;
import shop.titupet.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> createOrder(@RequestBody @Valid CreateOrderReq req){

        final EntityModel<OrderRes> entityModel = assembler
                .toModel(OrderDtoConverter.toResponse(orderService
                        .createOrder(req)));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping(ApiEndpoints.ORDER_V1)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> updateOrder(@RequestBody @Valid UpdateOrderReq req){
        final EntityModel<OrderRes> entityModel = assembler
                .toModel(OrderDtoConverter.toResponse(orderService.updateOrder(OrderDtoConverter.toEntity(req))));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
                        .toUri())
                .body(entityModel);
    }

    @DeleteMapping(ApiEndpoints.ORDER_V1 + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("id") Long id){
        orderService.deleteOrder(id);
    }

}
