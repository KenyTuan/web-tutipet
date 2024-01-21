package shop.titupet.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.titupet.config.exception.*;
import shop.titupet.models.converter.ProductDtoConverter;
import shop.titupet.models.dtos.product.CreateProductReq;
import shop.titupet.models.dtos.product.UpdateProductReq;
import shop.titupet.models.entities.Product;
import shop.titupet.models.entities.ProductType;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.repository.ProductRepo;
import shop.titupet.repository.ProductTypeRepo;
import shop.titupet.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    private final ProductTypeRepo typeRepo;

    // ============================ GET ALL PRODUCTS =============================
    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAllActive();
    }


    // ============================ GET PRODUCT BY ID ============================
    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("404","Not Found"));
    }

    // ============================ CRATE PRODUCT =================================
    @Override
    @Transactional
    public Product createProduct(CreateProductReq req) {

        final ProductType type = typeRepo.findById(req.getType_id())
                .orElseThrow(() -> new NotFoundException("404","Not Found"));

        try {
            final Product product = ProductDtoConverter.toEntity(req);

            product.setType(type);

            productRepo.save(product);

            return product;
        }catch (Exception e){
            throw new BadRequestException("400","Error Server"+ e);
        }

    }

    // ============================ DELETE PRODUCT ============================
    @Override
    @Transactional
    public void deleteProduct(Long id) {
        final Product product = productRepo.findById(id)
                .orElseThrow(()->new NotFoundException("404","Not Found"));

        try {

            product.setObjectStatus(ObjectStatus.DELETED);

            productRepo.save(product);
        }catch (Exception e){
            throw new BadRequestException("400","Error Server"+ e);
        }

    }

    // ============================ UPDATE PRODUCT ============================
    @Override
    @Transactional
    public Product updateProduct(UpdateProductReq req) {
        final Product product = productRepo.findById(req.getId())
                .orElseThrow(()->new NotFoundException("404","Not Found"));

        final ProductType type = (req.getType_id().equals(product.getType().getId()))?
                product.getType() : typeRepo.findById(req.getType_id())
                .orElseThrow(() -> new NotFoundException("404","Not Found"));

        try {
            // Update Obj Status into DELETED
            product.setObjectStatus(ObjectStatus.DELETED);
            productRepo.save(product);

            // Save Obj Updated
            Product newProduct = ProductDtoConverter.toEntity(req);
            newProduct.setType(type);
            productRepo.save(newProduct);

            return newProduct;
        }catch (Exception e){
            throw new BadRequestException("400","Error "+ e);
        }

    }

    // ============================ UPDATE STATUS PRODUCT ============================
    @Override
    public Product updateEnable(Long id, EnableStatus enable) {
        final Product product = productRepo.findById(id)
                .orElseThrow(()->new NotFoundException("404","Not Found"));
        try {
            product.setStatus(enable);
            productRepo.save(product);
            return product;
        }catch (Exception e){
            throw new BadRequestException("400","Error "+ e);
        }
    }

    // ============================ SEARCH NAME ============================
    @Override
    public Product getProductByName(String name) {
        return productRepo.findActiveByName(name)
                .orElseThrow(()->new NotFoundException("404","Not Found"));
    }
}
