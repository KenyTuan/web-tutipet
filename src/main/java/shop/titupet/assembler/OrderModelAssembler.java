package shop.titupet.assembler;

import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import shop.titupet.controller.OrderController;
import shop.titupet.models.dtos.order.OrderRes;
import shop.titupet.models.entities.Order;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderModelAssembler implements
        RepresentationModelAssembler<OrderRes, EntityModel<OrderRes>> {

    @Override
    @NonNull
    public EntityModel<OrderRes> toModel(@NonNull OrderRes entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(OrderController.class).getAllOrders()).withRel("orders")
            );
    }
}
