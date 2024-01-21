package shop.titupet.service;

import shop.titupet.models.entities.ProductType;

import java.util.List;

public interface ProductTypeService {

    List<ProductType> getAllTypeProduct();

    ProductType getTypeProductById(Integer id);

}
