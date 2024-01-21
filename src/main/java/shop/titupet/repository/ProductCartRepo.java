package shop.titupet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.titupet.models.entities.ProductCart;

@Repository
public interface ProductCartRepo extends JpaRepository<ProductCart,Long> {

}