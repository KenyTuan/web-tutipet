package shop.titupet.service;

//import org.springframework.security.core.userdetails.UserDetails;
import shop.titupet.models.dtos.auth.AuthReq;
import shop.titupet.models.dtos.auth.AuthRes;
import shop.titupet.models.dtos.auth.RegisterReq;
import shop.titupet.models.entities.User;

import java.util.Optional;

public interface AuthService {
    Optional<User> findByEmail(String email);

    String createUser(RegisterReq request);

    String authenticate(AuthReq request);

//    UserDetails loadUserByUsername(String username);
}
