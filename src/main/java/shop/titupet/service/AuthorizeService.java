package shop.titupet.service;

import shop.titupet.models.entities.User;

import java.util.Optional;

public interface AuthorizeService {
    Optional<User> findByEmail(String email);
}
