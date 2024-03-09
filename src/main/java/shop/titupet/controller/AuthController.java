package shop.titupet.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.models.converter.AuthDtoConverter;
import shop.titupet.models.dtos.auth.AuthReq;
import shop.titupet.models.dtos.auth.AuthRes;
import shop.titupet.models.dtos.auth.RegisterReq;
import shop.titupet.service.AuthService;

@RestController
@RequestMapping(ApiEndpoints.PREFIX)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(ApiEndpoints.AUTH_V1 + "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthRes register(
        @RequestBody @Valid RegisterReq request
    ){
        return AuthDtoConverter.toResponse(authService.createUser(request));
    }

    @PostMapping(ApiEndpoints.AUTH_V1 + "/authenticate")
    public AuthRes authenticate(
        @RequestBody AuthReq request
    ){
        return AuthDtoConverter.toResponse(authService.authenticate(request));
    }
}
