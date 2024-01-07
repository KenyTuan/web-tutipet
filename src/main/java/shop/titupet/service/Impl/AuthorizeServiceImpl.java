package shop.titupet.service.Impl;

import org.springframework.stereotype.Service;
import shop.titupet.models.entities.User;
import shop.titupet.service.AuthorizeService;

import java.util.Optional;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {
    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }
}
