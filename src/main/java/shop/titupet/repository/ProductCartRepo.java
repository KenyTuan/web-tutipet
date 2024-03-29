package shop.titupet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shop.titupet.models.entities.ProductCart;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductCartRepo extends JpaRepository<ProductCart,Long> {

    @Query("SELECT p " +
            "FROM ProductCart p " +
            "WHERE p.objectStatus = shop.titupet.models.enums.ObjectStatus.ACTIVE " +
            "AND p.cart.id = :cart_id AND p.product.id = :product_id")
    ProductCart findProductCartByIdAndAndCart_Id(@Param("cart_id") Long cart_id,
                                                 @Param("product_id") Long product_id);

    @Query("SELECT p from ProductCart p " +
            "where p.objectStatus = shop.titupet.models.enums.ObjectStatus.ACTIVE " +
            "and p.id = :id")
    Optional<ProductCart> findProductCarActiveById(@Param("id") Long id);


    @Query("select p from ProductCart p where p.id IN :productCartIds")
    List<ProductCart> findByIdIn(@Param("productCartIds") List<Long> productCartIds);

    @Query("select p from ProductCart p " +
            "where p.id in :productCartIds and  " +
            "p.objectStatus = shop.titupet.models.enums.ObjectStatus.ACTIVE")
    List<ProductCart> findAllActiveById(@Param("productCartIds") List<Long> productCartIds);


    @Query("select p from ProductCart p " +
            "where p.objectStatus = shop.titupet.models.enums.ObjectStatus.ACTIVE " +
            "and p.id = :productCartId and p.product.id = :productId")
    Optional<ProductCart> findActiveByIdAndProductId(
            @Param("productCartId") Long productCartId,
            @Param("productId") Long productId);

}
