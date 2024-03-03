package shop.titupet.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.titupet.exception.BadRequestException;
import shop.titupet.exception.NotFoundException;
import shop.titupet.models.dtos.user.CreateUserReq;
import shop.titupet.models.converter.UserDtoConverter;
import shop.titupet.models.dtos.user.UpdateUserPwdReq;
import shop.titupet.models.dtos.user.UpdateUserReq;
import shop.titupet.models.dtos.user.UserRes;
import shop.titupet.models.entities.Product;
import shop.titupet.models.entities.User;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.repository.UserRepository;
import shop.titupet.service.UserService;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllActiveUser();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("404", "Not Found"));
    }

    @Override
    @Transactional
    public User createUser(CreateUserReq req) {
        //Validation
        if (!Objects.equals(req.getPassword(), req.getConfirmPassword())){
            throw new BadRequestException("400","Password Doest Not Match!");
        }

        final boolean existedEmail = userRepository.existsByEmail(req.getEmail());

        if (existedEmail)
            throw new BadRequestException("400", "Email existed.");

        try {

            // Change request to entity
            final User user = UserDtoConverter.toEntity(req);
            userRepository.save(user);

            return user;
        }catch (Exception e){
            throw new BadRequestException("500","Error Server " + e);
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        try{
            final User user = userRepository.findById(id)
                    .orElseThrow(()-> new NotFoundException("404","User not found"));

            updateDeletedUserData(user);
            userRepository.save(user);
        }catch (Exception e){
            throw new BadRequestException("500","Error Server " + e);
        }

    }

    @Override
    public User updateInfoUser(UpdateUserReq req) {
        final User user = userRepository.findById(req.getId())
                .orElseThrow(()->new NotFoundException("404","Not Found"));

        try {
            user.setGender(req.isGender());
            user.setFullName(req.getFullName());
            user.setImg(req.getImg());

            userRepository.save(user);
            return user;
        }catch (Exception e){
            throw new BadRequestException("500","Error "+ e);
        }
    }

    @Override
    public User updateUserPwd(UpdateUserPwdReq req) {
        final User user = userRepository.findById(req.getId())
                .orElseThrow(()->new NotFoundException("404","Not Found!"));

        if (!user.getPassword().equals(req.getOldPassword())){
            throw new BadRequestException("400","Old password does not match!");
        }

        if (!req.getOldPassword().equals(req.getConfirmPassword())){
            throw new BadRequestException("400","Confirm Password does not match!");
        }

        try {
            user.setPassword(req.getNewPassword());

            userRepository.save(user);
            return user;
        }catch (Exception e){
            throw new BadRequestException("500","Error "+ e);
        }
    }

    private void updateDeletedUserData(User user) {
        user.setObjectStatus(ObjectStatus.DELETED);
        user.setEmail("deleted-" + UUID.randomUUID() + user.getEmail());
    }
}
