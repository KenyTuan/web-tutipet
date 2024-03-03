package shop.titupet.service.Impl;

import lombok.RequiredArgsConstructor;

//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import shop.titupet.models.entities.CustomUserDetails;
import shop.titupet.exception.BadRequestException;
import shop.titupet.exception.NotFoundException;
import shop.titupet.security.JwtService;
import shop.titupet.models.converter.AuthDtoConverter;
import shop.titupet.models.dtos.auth.AuthReq;
import shop.titupet.models.dtos.auth.RegisterReq;
import shop.titupet.models.entities.User;
import shop.titupet.repository.UserRepository;
import shop.titupet.service.AuthService;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

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

        return jwtService.generateToken( user);
    }

    @Override
    public String authenticate(AuthReq request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        final User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new NotFoundException("404", "Not Found!"));



        return jwtService.generateToken(user);
    }

}
