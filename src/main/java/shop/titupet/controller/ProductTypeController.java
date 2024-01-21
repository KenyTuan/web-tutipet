package shop.titupet.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.models.converter.ProductTypeDtoConverter;
import shop.titupet.models.dtos.productType.ProductTypeRes;
import shop.titupet.models.entities.ProductType;
import shop.titupet.service.ProductTypeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiEndpoints.PREFIX)
@RequiredArgsConstructor
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @GetMapping(ApiEndpoints.PRO_TYPE_V1)
    public List<ProductType> getAllTypeProducts(){
        return productTypeService.getAllTypeProduct();
    }


    @GetMapping(ApiEndpoints.PRO_TYPE_V1+ "/{id}")
    public ProductTypeRes getTypeProductById(@PathVariable("id") Integer id){
        return ProductTypeDtoConverter.toResponse(productTypeService.getTypeProductById(id));
    }


}
