package shop.titupet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.titupet.models.entities.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long> {
}
