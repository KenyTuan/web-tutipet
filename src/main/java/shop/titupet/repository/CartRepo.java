package shop.titupet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shop.titupet.models.entities.Cart;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {

    @Query("SELECT c " +
            "FROM Cart c " +
            "JOIN FETCH c.productCarts pc " +
            "WHERE c.objectStatus = shop.titupet.models.enums.ObjectStatus.ACTIVE " +
            "AND pc.objectStatus = shop.titupet.models.enums.ObjectStatus.ACTIVE " +
            "AND c.user.id = :user_id")
    Optional<Cart> findCartActiveByUser_Id(@Param("user_id") Long user_id);

    @Query("SELECT c " +
            "FROM Cart c " +
            "WHERE c.objectStatus = shop.titupet.models.enums.ObjectStatus.ACTIVE " +
            "AND c.user.id = :user_id")
    Optional<Cart> findCartByUser_Id(@Param("user_id") Long user_id);

    @Query("SELECT c " +
            "from Cart c inner" +
            " join ProductCart p on c.id = p.cart.id " +
            "WHERE c.objectStatus = shop.titupet.models." +
            "enums.ObjectStatus.ACTIVE and " +
            "p.objectStatus = shop.titupet.models." +
            "enums.ObjectStatus.ACTIVE")
    List<Cart> findAllByActiveCart();

}
