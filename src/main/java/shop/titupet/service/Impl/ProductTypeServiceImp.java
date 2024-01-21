package shop.titupet.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.titupet.config.exception.NotFoundException;
import shop.titupet.models.entities.ProductType;
import shop.titupet.repository.ProductTypeRepo;
import shop.titupet.service.ProductTypeService;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductTypeServiceImp implements ProductTypeService {

    private final ProductTypeRepo repo;

    @Override
    public List<ProductType> getAllTypeProduct() {
        return repo.findAll();
    }

    @Override
    public ProductType getTypeProductById(Integer id) {
        return repo.findById(id).orElseThrow(()-> new NotFoundException("404","Not Found"));
    }

}
