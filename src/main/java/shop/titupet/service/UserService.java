package shop.titupet.service;

import shop.titupet.models.dtos.user.CreateUserReq;
import shop.titupet.models.dtos.user.UserRes;
import shop.titupet.models.entities.User;

import java.util.List;

public interface UserService {
    List<UserRes> getAllUsers();

    User getUserById(Long id);

    User createUser(CreateUserReq user);

    void deleteUser(Long id);

    User updateUser(Long id,User user);

}
