package shop.titupet.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.titupet.config.exception.NotFoundException;
import shop.titupet.models.entities.ProductType;
import shop.titupet.repository.TypeProductRepo;
import shop.titupet.service.TypeProductService;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TypeProductServiceImp implements TypeProductService {

    private final TypeProductRepo repo;

    @Override
    public List<ProductType> getAllTypeProduct() {
        return repo.findAll();
    }

    @Override
    public ProductType getTypeProductById(Integer id) {
        return repo.findById(id).orElseThrow(()-> new NotFoundException("404","Not Found"));
    }

}
