package shop.titupet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.titupet.models.entities.ProductType;

@Repository
public interface TypeProductRepo extends JpaRepository<ProductType,Integer> {
}
