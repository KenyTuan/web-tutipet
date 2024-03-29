package shop.titupet.service;

import shop.titupet.dtos.user.CreateUserReq;
import shop.titupet.dtos.user.UpdateUserPwdReq;
import shop.titupet.dtos.user.UpdateUserReq;
import shop.titupet.models.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    User createUser(CreateUserReq user);

    void deleteUser(Long id);
    User updateInfoUser(UpdateUserReq req, String token);

    User updateUserPwd(UpdateUserPwdReq req,String token);

    User getInfoUser(String token);
}
