package shop.titupet.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.titupet.config.exception.NotFoundException;
import shop.titupet.models.converter.ProductDtoConverter;
import shop.titupet.models.dtos.product.CreateProductReq;
import shop.titupet.models.dtos.product.UpdateProductReq;
import shop.titupet.models.entities.Product;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.repository.ProductRepo;
import shop.titupet.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepo.findAllActive();
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("404","Not Found"));

        return product;
    }

    @Override
    public Product createProduct(CreateProductReq req) {

        Product product = ProductDtoConverter.toEntity(req);

        productRepo.save(product);

        return product;
    }

    @Override
    public void deleteProduct(Long id) {

        Product product = productRepo.findById(id)
                .orElseThrow(()->new NotFoundException("404","Not Found"));

        product.setObjectStatus(ObjectStatus.DELETED);
        productRepo.save(product);
    }

    @Override
    public Product updateProduct(UpdateProductReq req) {

        Product product = productRepo.findById(req.getId())
                .orElseThrow(()->new NotFoundException("404","Not Found"));

        product.setObjectStatus(ObjectStatus.DELETED);
        productRepo.save(product);

        Product newProduct = ProductDtoConverter.toEntity(req);
        newProduct.setStatus(product.getStatus());

        productRepo.save(newProduct);

        return newProduct;
    }
}
