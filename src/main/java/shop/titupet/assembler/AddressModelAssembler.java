package shop.titupet.assembler;

import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import shop.titupet.controller.AddressController;
import shop.titupet.dtos.address.AddressRes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AddressModelAssembler implements
        RepresentationModelAssembler<AddressRes, EntityModel<AddressRes>> {
    @Override
    @NonNull
    public EntityModel<AddressRes> toModel(@NonNull AddressRes entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(AddressController.class).getAddressById(entity.id())).withSelfRel(),
                linkTo(methodOn(AddressController.class).getAllAddress()).withRel("addresses"));
    }
}
