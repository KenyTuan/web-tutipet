package shop.titupet.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import shop.titupet.config.exception.BadRequestException;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.models.converter.ProductDtoConverter;
import shop.titupet.models.dtos.product.CreateProductReq;
import shop.titupet.models.dtos.product.ProductRes;
import shop.titupet.models.dtos.product.UpdateProductReq;
import shop.titupet.models.entities.Product;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = ApiEndpoints.PREFIX)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // ============================ GET LIST PRODUCT =============================
    @GetMapping(ApiEndpoints.PRODUCT_V1)
    public List<ProductRes> getAllProducts(){
        return productService.getAllProducts()
                .stream()
                .map(ProductDtoConverter::toResponse)
                .toList();
    }

    // ============================ GET PRODUCT BY ID=============================
    @GetMapping(value = ApiEndpoints.PRODUCT_V1 + "/{id}")
    public ProductRes getProductById(@PathVariable(name = "id") Long id){

        final Product product = productService.getProductById(id);

        return ProductDtoConverter.toResponse(product);
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
    public ProductRes postProduct(@RequestBody @Valid CreateProductReq req){

        return ProductDtoConverter.toResponse(
                productService.createProduct(req));
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
    public ProductRes updateProduct( @RequestBody @Valid UpdateProductReq req){

        final Product product = productService.updateProduct(req);

        return ProductDtoConverter.toResponse(product);
    }

    // ============================ PATCH PRODUCT =============================
    @PatchMapping(ApiEndpoints.PRODUCT_V1)
    public ProductRes updateEnableProduct(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "enable") EnableStatus enable
    ){
        return ProductDtoConverter.toResponse(productService.updateEnable(id,enable));
    }
}
