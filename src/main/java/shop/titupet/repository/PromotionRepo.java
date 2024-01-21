package shop.titupet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shop.titupet.models.entities.Promotion;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepo extends JpaRepository<Promotion, Long> {

    @Query(value = "select p from Promotion p where p.objectStatus = shop.titupet.models.enums.ObjectStatus.ACTIVE " +
            "and p.enableStatus = shop.titupet.models.enums.EnableStatus.ENABLED")
    List<Promotion> findAllActive();

    @Query(value = "select p from Promotion p where p.objectStatus = shop.titupet.models.enums.ObjectStatus.ACTIVE " +
            "and p.enableStatus = shop.titupet.models.enums.EnableStatus.ENABLED " +
            "and p.id = :id")
    Optional<Promotion> findActiveById(Long id);
}
