package shop.titupet.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.models.entities.ProductType;
import shop.titupet.service.TypeProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(ApiEndpoints.PREFIX)
@RequiredArgsConstructor
public class TypeProductController {

    private final TypeProductService typeProductService;

    @GetMapping(ApiEndpoints.PRO_TYPE_V1)
    public List<ProductType> getAllTypeProducts(){
        return typeProductService.getAllTypeProduct();
    }


    @GetMapping(ApiEndpoints.PRO_TYPE_V1+ "/{id}")
    public ProductType getTypeProductById(@PathVariable("id") Integer id){
        return null;
    }


}
