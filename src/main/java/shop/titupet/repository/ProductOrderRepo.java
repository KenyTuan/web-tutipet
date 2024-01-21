package shop.titupet.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.titupet.models.entities.ProductOrder;

@Repository
public interface ProductOrderRepo extends JpaRepository<ProductOrder, Long> {
}
