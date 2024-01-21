package shop.titupet.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public List<UserRes> getAllUsers() {
        List<User> users = userRepository.findAllActiveUser();
        return users.stream().map(UserDtoConverter::toResponse).toList();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("404", "Not Found"));
    }

    @Override
    @Transactional()
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
    @Transactional
    public User updateUser(Long id, User user) {
        return null;
    }

    private void updateDeletedUserData(User user) {
        user.setObjectStatus(ObjectStatus.DELETED);
        user.setEmail("deleted-" + UUID.randomUUID() + user.getEmail());
    }
}
