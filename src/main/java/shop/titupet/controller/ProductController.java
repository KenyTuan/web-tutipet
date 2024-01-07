package shop.titupet.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.models.converter.ProductDtoConverter;
import shop.titupet.models.dtos.product.CreateProductReq;
import shop.titupet.models.dtos.product.ProductRes;
import shop.titupet.models.dtos.product.UpdateProductReq;
import shop.titupet.models.entities.Product;
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

    @GetMapping(ApiEndpoints.PRODUCT_V1)
    public List<ProductRes> getAllProducts(){
        return productService.getAllProducts()
                .stream()
                .map(ProductDtoConverter::toResponse)
                .toList();
    }


    @GetMapping(ApiEndpoints.PRODUCT_V1+"/{id}")
    public ProductRes getProductById(@PathVariable("id") Long id){
        final Product product = productService.getProductById(id);

        return ProductDtoConverter.toResponse(product);
    }

    @PostMapping(ApiEndpoints.PRODUCT_V1)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductRes postProduct(@RequestBody @Valid CreateProductReq req){

        return ProductDtoConverter.toResponse(
                productService.createProduct(req));
    }

    @DeleteMapping(ApiEndpoints.PRODUCT_V1 + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }

    @PutMapping( ApiEndpoints.PRODUCT_V1)
    public ProductRes updateProduct( @RequestBody @Valid UpdateProductReq req){
        Product product = productService.updateProduct(req);

        return ProductDtoConverter.toResponse(product);
    }

}
