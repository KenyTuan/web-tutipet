package shop.titupet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shop.titupet.models.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(@Param("email") String email);

    @Query("SELECT u from User u WHERE u.objectStatus = shop.titupet.models.enums.ObjectStatus.ACTIVE")
    List<User> findAllActiveUser();



    @Query("SELECT u from User u WHERE u.objectStatus = shop.titupet.models.enums.ObjectStatus.ACTIVE" +
            " and u.email = :email")
    Optional<User> findByEmail(String email);


    @Query("SELECT u FROM  User u WHERE u.objectStatus = shop.titupet.models.enums.ObjectStatus.ACTIVE " +
            "and u.id = :userId")
    Optional<User> findUserActiveById(Long userId);
}
