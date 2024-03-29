package shop.titupet.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import shop.titupet.assembler.ProductTypeModelAssembler;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.converter.ProductTypeDtoConverter;
import shop.titupet.dtos.productType.ProductTypeRes;
import shop.titupet.models.entities.ProductType;
import shop.titupet.service.ProductTypeService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(ApiEndpoints.PREFIX)
@RequiredArgsConstructor
public class ProductTypeController {

    private final ProductTypeService productTypeService;
    private final ProductTypeModelAssembler assembler;
    @GetMapping(ApiEndpoints.PRO_TYPE_V1)
    public CollectionModel<EntityModel<ProductTypeRes>> getAllTypeProducts(){

        final List<ProductTypeRes> typeRes =productTypeService.getAllTypeProduct()
                .stream()
                .map(ProductTypeDtoConverter::toResponse)
                .toList();

        final List<EntityModel<ProductTypeRes>> employees = typeRes
                .stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(
                employees,
                linkTo(methodOn(ProductTypeController.class).getAllTypeProducts())
                        .withSelfRel());
    }


    @GetMapping(ApiEndpoints.PRO_TYPE_V1+ "/{id}")
    public EntityModel<ProductTypeRes> getTypeProductById(@PathVariable("id") Integer id){
        final ProductType type = productTypeService.getTypeProductById(id);
        return assembler.toModel(ProductTypeDtoConverter.toResponse(type));
    }


}
