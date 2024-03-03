package shop.titupet.service;

import shop.titupet.models.dtos.product.CreateProductReq;
import shop.titupet.models.dtos.product.ProductRes;
import shop.titupet.models.dtos.product.UpdateProductReq;
import shop.titupet.models.entities.Product;
import shop.titupet.models.enums.EnableStatus;

import java.util.List;

public interface ProductService  {

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product createProduct(CreateProductReq product);

    void deleteProduct(Long id);

    Product updateProduct(UpdateProductReq req);

    Product updateEnable(Long id, EnableStatus enable);

    Product getProductByName( String name);

    List<ProductRes> getListProductLimit(Integer page, Integer limit);

    List<Product> findAll(Integer size, Long page);
}
