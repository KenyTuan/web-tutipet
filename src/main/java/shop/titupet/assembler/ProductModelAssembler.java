package shop.titupet.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import shop.titupet.controller.ProductController;
import shop.titupet.models.dtos.product.ProductRes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductModelAssembler implements
        RepresentationModelAssembler<ProductRes, EntityModel<ProductRes>> {
    @Override
    public EntityModel<ProductRes> toModel(ProductRes entity) {

        return EntityModel.of(entity,
                linkTo(methodOn(ProductController.class).getProductById(entity.id())).withSelfRel(),
                linkTo(methodOn(ProductController.class).getAllProducts()).withRel("products"));
    }
}
