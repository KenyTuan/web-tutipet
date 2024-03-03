package shop.titupet.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import shop.titupet.controller.ProductTypeController;
import shop.titupet.controller.UserController;
import shop.titupet.models.dtos.user.UserRes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements
        RepresentationModelAssembler<UserRes, EntityModel<UserRes>> {
    @Override
    public EntityModel<UserRes> toModel(UserRes entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(UserController.class).getUserById(entity.id())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("users"));
    }
}
