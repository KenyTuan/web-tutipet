package shop.titupet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shop.titupet.models.entities.Address;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long> {
    @Query(value = "select a from Address a where a.objectStatus = shop.titupet.models.enums.ObjectStatus.ACTIVE")
    List<Address> findAllActive();

    @Query(value = "select a from Address a where a.objectStatus = shop.titupet.models.enums.ObjectStatus.ACTIVE " +
            "and a.id = :id")
    Optional<Address> findActiveById(Long id);

    @Query(value = "select a from Address a where a.objectStatus = shop.titupet.models.enums.ObjectStatus.ACTIVE " +
            "and a.user.id = :userId")
    Optional<Address> findActiveByUserID(Long userId);
}
