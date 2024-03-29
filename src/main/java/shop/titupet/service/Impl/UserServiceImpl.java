package shop.titupet.service.Impl;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.titupet.exception.BadRequestException;
import shop.titupet.exception.NotFoundException;
import shop.titupet.dtos.user.CreateUserReq;
import shop.titupet.converter.UserDtoConverter;
import shop.titupet.dtos.user.UpdateUserPwdReq;
import shop.titupet.dtos.user.UpdateUserReq;
import shop.titupet.models.entities.User;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.repository.UserRepository;
import shop.titupet.security.JwtService;
import shop.titupet.service.UserService;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
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
    public User updateInfoUser(UpdateUserReq req, String token) {
        try {
            if (token == null || !token.startsWith("Bearer ")) {
                throw new BadRequestException("400", "Invalid or missing Authorization header");
            }

            token = token.substring(7);
            Claims claims = jwtService.decodeToken(token);

            Long userId = ((Number) claims.get("userId")).longValue();

            final User user = userRepository.getReferenceById(userId);
            final User newUser = new User();
            BeanUtils.copyProperties(user, newUser);

            updateDeletedUserData(user);

            newUser.setGender(req.isGender());
            newUser.setFullName(req.getFullName());

            userRepository.save(user);
            userRepository.save(newUser);
            return newUser;
        }catch (Exception e){
            throw new BadRequestException("500","Error "+ e);
        }
    }

    @Override
    public User updateUserPwd(UpdateUserPwdReq req, String token) {

        if (token == null || !token.startsWith("Bearer ")) {
            throw new BadRequestException("400", "Invalid or missing Authorization header");
        }

        token = token.substring(7);
        Claims claims = jwtService.decodeToken(token);

        final Long userId = ((Number) claims.get("userId")).longValue();

        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("404","User exits!"));

        if (!passwordEncoder.matches(req.getOldPassword(), user.getPassword())) {
            throw new BadRequestException("400", "Incorrect old password!");
        }

        if (!req.getNewPassword().equals(req.getConfirmPassword())){
            throw new BadRequestException("400","Confirm Password does not match!");
        }

        try {

            user.setPassword(passwordEncoder.encode(req.getNewPassword()));

            userRepository.save(user);
            return user;
        }catch (Exception e){
            throw new BadRequestException("500","Error "+ e);
        }
    }

    @Override
    public User getInfoUser(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new BadRequestException("400", "Invalid or missing Authorization header");
        }

        token = token.substring(7);
        Claims claims = jwtService.decodeToken(token);

        Long userId = ((Number) claims.get("userId")).longValue();

        return userRepository.getReferenceById(userId);
    }

    private void updateDeletedUserData(User user) {
        user.setObjectStatus(ObjectStatus.DELETED);
        user.setEmail("deleted-" + UUID.randomUUID() + user.getEmail());
    }

}
