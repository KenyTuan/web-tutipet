package shop.titupet.controller;


import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.titupet.assembler.ProductModelAssembler;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.models.converter.ProductDtoConverter;
import shop.titupet.models.dtos.product.CreateProductReq;
import shop.titupet.models.dtos.product.ProductRes;
import shop.titupet.models.dtos.product.UpdateProductReq;
import shop.titupet.models.entities.Product;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.service.ProductService;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = ApiEndpoints.PREFIX)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductModelAssembler assembler;

    // ============================ GET LIST PRODUCT =============================
    @GetMapping(ApiEndpoints.PRODUCT_V1)
    public CollectionModel<EntityModel<ProductRes>> getAllProducts(){
        final List<ProductRes> proRes =productService.getAllProducts()
                .stream()
                .map(ProductDtoConverter::toResponse)
                .toList();

        final List<EntityModel<ProductRes>> entityModels = proRes
                .stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(
                entityModels,
                        linkTo(methodOn(ProductController.class).getAllProducts())
                                .withSelfRel());
    }


    @GetMapping(value = ApiEndpoints.PRODUCT_V1, params = {"size", "page","sort"})
    public List<Product> getAllProductsLimitPage(@RequestParam(name = "size") Integer size,
                                                 @RequestParam(name = "page") Long page,
                                                 @RequestParam(name = "sort") String sort
                                                 ){
        return productService.findAll(size,page);
    }

    // ============================ GET PRODUCT BY ID=============================
    @GetMapping(value = ApiEndpoints.PRODUCT_V1 + "/{id}")
    public EntityModel<ProductRes> getProductById(@PathVariable(name = "id") Long id){

        final Product product = productService.getProductById(id);

        return assembler.toModel(ProductDtoConverter.toResponse(product));
    }

    // ============================ GET PRODUCT BY NAME =============================
//    @GetMapping(value = ApiEndpoints.PRODUCT_V1,params = "name")
    public ProductRes getProductByName(@RequestParam(name = "name") String name){

        final Product product = productService.getProductByName(name);

        return ProductDtoConverter.toResponse(product);
    }

    // ============================ POST PRODUCT =============================
    @PostMapping(ApiEndpoints.PRODUCT_V1)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> postProduct(@RequestBody @Valid CreateProductReq req){

        final EntityModel<ProductRes> entityModel = assembler.toModel(ProductDtoConverter
                .toResponse(productService.createProduct(req)));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    // ============================ DELETE PRODUCT =============================
    @DeleteMapping(ApiEndpoints.PRODUCT_V1 + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }

    // ============================ PUT PRODUCT =============================
    @PutMapping( ApiEndpoints.PRODUCT_V1)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateProduct( @RequestBody @Valid UpdateProductReq req){

        final EntityModel<ProductRes> entityModel = assembler
                .toModel(ProductDtoConverter
                        .toResponse(productService.updateProduct(req)));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    // ============================ PATCH PRODUCT =============================
    @PatchMapping(ApiEndpoints.PRODUCT_V1)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateEnableProduct(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "enable") EnableStatus enable
    ){
        final EntityModel<ProductRes> entityModel = assembler
                .toModel(ProductDtoConverter
                                .toResponse(productService.updateEnable(id,enable)));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
