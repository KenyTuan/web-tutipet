package shop.titupet.assembler;

import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import shop.titupet.controller.PromotionController;
import shop.titupet.dtos.promotion.PromotionRes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class PromotionModelAssembler implements
        RepresentationModelAssembler<PromotionRes, EntityModel<PromotionRes>> {
    @Override
    @NonNull
    public EntityModel<PromotionRes> toModel(@NonNull PromotionRes entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(PromotionController.class).getPromotion(entity.id())).withSelfRel(),
                linkTo(methodOn(PromotionController.class).getAllPromotion()).withRel("promotions"));
    }

}
