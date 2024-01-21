package shop.titupet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shop.titupet.models.entities.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    @Query(value = "select p FROM Product p " +
            "where p.objectStatus = shop.titupet.models.enums.ObjectStatus.ACTIVE " +
            "and p.status = shop.titupet.models.enums.EnableStatus.ENABLED")
    List<Product> findAllActive();

    @Query(value = "select p from Product p " +
            "where p.id = :id and p.objectStatus =  shop.titupet.models.enums.ObjectStatus.ACTIVE " +
            "and p.status = shop.titupet.models.enums.EnableStatus.ENABLED")
    Optional<Product> findActiveById(Long id);

    @Query(value = "select p from Product p " +
            "where p.objectStatus = shop.titupet.models.enums.ObjectStatus.ACTIVE " +
            "and p.status = shop.titupet.models.enums.EnableStatus.ENABLED " +
            "and p.name like :name")
    Optional<Product> findActiveByName(@Param(value = "name") String name);
}
