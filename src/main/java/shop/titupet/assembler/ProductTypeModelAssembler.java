package shop.titupet.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import shop.titupet.controller.ProductTypeController;
import shop.titupet.models.dtos.productType.ProductTypeRes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class ProductTypeModelAssembler implements
        RepresentationModelAssembler<ProductTypeRes, EntityModel<ProductTypeRes>> {
    @Override
    public EntityModel<ProductTypeRes> toModel(ProductTypeRes entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ProductTypeController.class).getTypeProductById(entity.id())).withSelfRel(),
                linkTo(methodOn(ProductTypeController.class).getAllTypeProducts()).withRel("productTypes"));
    }
}
