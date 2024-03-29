package shop.titupet.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.converter.AuthDtoConverter;
import shop.titupet.dtos.auth.AuthReq;
import shop.titupet.dtos.auth.AuthRes;
import shop.titupet.dtos.auth.RegisterReq;
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
        return AuthDtoConverter.toResponse(authService.createUser(request),604800000L);
    }

    @PostMapping(ApiEndpoints.AUTH_V1 + "/authenticate")
    public AuthRes authenticate(
        @RequestBody AuthReq request
    ){
        return AuthDtoConverter.toResponse(authService.authenticate(request),604800000L);
    }
}
