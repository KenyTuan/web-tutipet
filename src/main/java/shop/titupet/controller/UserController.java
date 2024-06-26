package shop.titupet.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.titupet.assembler.UserModelAssembler;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.converter.UserDtoConverter;
import shop.titupet.dtos.user.CreateUserReq;
import shop.titupet.dtos.user.UpdateUserPwdReq;
import shop.titupet.dtos.user.UpdateUserReq;
import shop.titupet.dtos.user.UserRes;
import shop.titupet.service.UserService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = ApiEndpoints.PREFIX)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserModelAssembler assembler;

    @GetMapping(ApiEndpoints.USER_V1)
    public CollectionModel<EntityModel<UserRes>> getAllUsers(){

        final List<UserRes> res =userService.getAllUsers()
                .stream()
                .map(UserDtoConverter::toResponse)
                .toList();

        final List<EntityModel<UserRes>> employees = res
                .stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(employees,
                linkTo(methodOn(UserController.class).getAllUsers())
                        .withSelfRel());
    }


    @GetMapping(ApiEndpoints.USER_V1 + "/{id}")
    public EntityModel<UserRes> getUserById(@PathVariable Long id){
        final UserRes user = UserDtoConverter.toResponse(userService.getUserById(id));
        return assembler.toModel(user);
    }

    @GetMapping(ApiEndpoints.USER_V1 + "/info")
    public EntityModel<UserRes> getUser(@RequestHeader("Authorization") String token){
        final UserRes user = UserDtoConverter.toResponse(userService.getInfoUser(token));
        return assembler.toModel(user);
    }

    @PostMapping(ApiEndpoints.USER_V1)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> postUser(@RequestBody @Valid CreateUserReq req){

        final EntityModel<UserRes> entityModel = assembler.toModel(UserDtoConverter
                .toResponse(userService.createUser(req)));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping( ApiEndpoints.USER_V1 + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PutMapping(ApiEndpoints.USER_V1)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateUser(@RequestBody @Valid UpdateUserReq req,
                                        @RequestHeader("Authorization") String token){
        final EntityModel<UserRes> entityModel = assembler
                .toModel(UserDtoConverter
                        .toResponse(userService.updateInfoUser(req, token)));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping(ApiEndpoints.USER_V1 + "/change_password")
    @ResponseStatus(HttpStatus.OK)
    public UserRes updateUserPassword(@RequestBody @Valid UpdateUserPwdReq req,
                                                @RequestHeader("Authorization") String token){

        return UserDtoConverter
                .toResponse(userService.updateUserPwd(req,token));
    }


}
