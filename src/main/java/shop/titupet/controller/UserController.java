package shop.titupet.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.models.converter.UserDtoConverter;
import shop.titupet.models.dtos.user.CreateUserReq;
import shop.titupet.models.dtos.user.UserRes;
import shop.titupet.models.entities.User;
import shop.titupet.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = ApiEndpoints.PREFIX)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(ApiEndpoints.USER_V1)
    public List<UserRes> getAllUsers(){

        return userService.getAllUsers();
    }


    @GetMapping(ApiEndpoints.USER_V1 + "/{id}")
    public UserRes getUserById(@PathVariable Long id){
        final User user = userService.getUserById(id);
        return UserDtoConverter.toResponse(user);
    }

    @PostMapping(ApiEndpoints.USER_V1)
    @ResponseStatus(HttpStatus.CREATED)
    public UserRes postUser(@RequestBody @Valid CreateUserReq req){
        return UserDtoConverter.toResponse(userService.createUser(req));
    }

    @DeleteMapping( ApiEndpoints.USER_V1 + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

//    @PutMapping(ApiEndpoints.USER_V1+"{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                           @RequestBody User user){
        user = userService.updateUser(id,user);

        return ResponseEntity.ok(user);
    }


}
