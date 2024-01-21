package shop.titupet.service.Impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import shop.titupet.config.exception.NotFoundException;
import shop.titupet.models.entities.Role;
import shop.titupet.models.entities.User;
import shop.titupet.repository.UserRepository;
import shop.titupet.service.AuthorizeService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizeServiceImpl implements AuthorizeService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }
}
