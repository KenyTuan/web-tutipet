package shop.titupet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shop.titupet.models.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(@Param("email") String email);

    @Query("SELECT u from User u WHERE u.objectStatus = shop.titupet.models.enums.ObjectStatus.ACTIVE ")
    List<User> findAllActiveUser();
}
