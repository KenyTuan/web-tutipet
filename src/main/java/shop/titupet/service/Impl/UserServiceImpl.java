package shop.titupet.service.Impl;

import org.springframework.stereotype.Service;
import shop.titupet.config.exception.BadRequestException;
import shop.titupet.config.exception.NotFoundException;
import shop.titupet.models.dtos.user.CreateUserReq;
import shop.titupet.models.converter.UserDtoConverter;
import shop.titupet.models.dtos.user.UserRes;
import shop.titupet.models.entities.User;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.repository.UserRepository;
import shop.titupet.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserRes> getAllUsers() {
        List<User> users = userRepository.findAllActiveUser();
        return users.stream().map(UserDtoConverter::toResponse).toList();
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("404", "Not Found"));
        return user;
    }

    @Override
    public User createUser(CreateUserReq req) {
        //Validation
        final boolean existedEmail = userRepository.existsByEmail(req.getEmail());

        if (existedEmail)
            throw new BadRequestException("400", "Email existed.");

        final User user = UserDtoConverter.toEntity(req);
        userRepository.save(user);

        return user;
    }

    @Override
    public void deleteUser(Long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("404","User not found"));

        updateDeletedUserData(user);
        userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        return null;
    }

    private void updateDeletedUserData(User user) {
        user.setObjectStatus(ObjectStatus.DELETED);
        user.setEmail("deleted-" + UUID.randomUUID() + user.getEmail());
    }
}
