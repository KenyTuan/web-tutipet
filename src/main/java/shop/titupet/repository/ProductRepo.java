package shop.titupet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shop.titupet.models.entities.Product;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    @Query(value = "select p FROM Product p " +
            "where p.objectStatus = shop.titupet.models.enums.ObjectStatus.ACTIVE " +
            "and p.status = shop.titupet.models.enums.EnableStatus.ENABLED")
    List<Product> findAllActive();
}
