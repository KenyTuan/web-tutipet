package shop.titupet.service;

import shop.titupet.models.dtos.user.CreateUserReq;
import shop.titupet.models.dtos.user.UpdateUserPwdReq;
import shop.titupet.models.dtos.user.UpdateUserReq;
import shop.titupet.models.dtos.user.UserRes;
import shop.titupet.models.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    User createUser(CreateUserReq user);

    void deleteUser(Long id);
    User updateInfoUser(UpdateUserReq req);

    User updateUserPwd(UpdateUserPwdReq req);
}
