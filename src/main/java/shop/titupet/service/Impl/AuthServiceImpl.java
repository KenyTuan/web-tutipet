package shop.titupet.service.Impl;

import lombok.RequiredArgsConstructor;

//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import shop.titupet.models.entities.CustomUserDetails;
import org.springframework.transaction.annotation.Transactional;
import shop.titupet.exception.BadRequestException;
import shop.titupet.exception.NotFoundException;
import shop.titupet.models.entities.Cart;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.repository.CartRepo;
import shop.titupet.security.JwtService;
import shop.titupet.converter.AuthDtoConverter;
import shop.titupet.dtos.auth.AuthReq;
import shop.titupet.dtos.auth.RegisterReq;
import shop.titupet.models.entities.User;
import shop.titupet.repository.UserRepository;
import shop.titupet.service.AuthService;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CartRepo cartRepo;



    @Override
    public String createUser(RegisterReq request) {
        if (!Objects.equals(request.getPassword(), request.getConfirmPassword())){
            throw new BadRequestException("400","Password Doest Not Match!");
        }
        final boolean existedEmail = userRepository.existsByEmail(request.getEmail());
        if (existedEmail)
            throw new BadRequestException("400", "Email existed.");

        final User user = AuthDtoConverter.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        final User newUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new BadRequestException("400", "Account existed!"));

        Cart cart = Cart.builder().user(newUser).build();
        cart.setObjectStatus(ObjectStatus.ACTIVE);
        cartRepo.save(cart);

        return jwtService.generateToken(user);
    }

    @Override
    public String authenticate(AuthReq request) {
        final User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new NotFoundException("404", "Not Found!"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );


        return jwtService.generateToken(user);
    }

}
