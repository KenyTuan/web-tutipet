package shop.titupet.service;

//import org.springframework.security.core.userdetails.UserDetails;
import shop.titupet.dtos.auth.AuthReq;
import shop.titupet.dtos.auth.RegisterReq;

public interface AuthService {

    String createUser(RegisterReq request);

    String authenticate(AuthReq request);

//    UserDetails loadUserByUsername(String username);
}
