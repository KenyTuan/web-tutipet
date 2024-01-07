package shop.titupet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.titupet.models.entities.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
}
