package shop.titupet.service;

import shop.titupet.models.dtos.product.CreateProductReq;
import shop.titupet.models.dtos.product.UpdateProductReq;
import shop.titupet.models.entities.Product;

import java.util.List;

public interface ProductService  {

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product createProduct(CreateProductReq product);

    void deleteProduct(Long id);

    Product updateProduct(UpdateProductReq req);
}
