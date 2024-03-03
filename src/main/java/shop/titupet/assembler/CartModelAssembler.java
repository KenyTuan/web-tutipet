package shop.titupet.assembler;

import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import shop.titupet.controller.AddressController;
import shop.titupet.controller.CartController;
import shop.titupet.models.dtos.address.AddressRes;
import shop.titupet.models.dtos.cart.CartRes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CartModelAssembler implements
        RepresentationModelAssembler<CartRes, EntityModel<CartRes>> {

    @Override
    @NonNull
    public EntityModel<CartRes> toModel(@NonNull CartRes entity) {

        return EntityModel.of(entity,
                linkTo(methodOn(CartController.class).getCartById(entity.id())).withSelfRel(),
                linkTo(methodOn(CartController.class).getAllCart()).withRel("carts"));
    }
}
