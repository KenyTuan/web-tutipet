package shop.titupet.service;

import shop.titupet.models.entities.ProductType;

import java.util.List;

public interface TypeProductService {

    List<ProductType> getAllTypeProduct();

    ProductType getTypeProductById(Integer id);

}
