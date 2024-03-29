package shop.titupet.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.titupet.assembler.AddressModelAssembler;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.converter.AddressDtoConverter;
import shop.titupet.dtos.address.AddressRes;
import shop.titupet.dtos.address.CreateAddressReq;
import shop.titupet.dtos.address.UpdateAddressReq;
import shop.titupet.service.AddressService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(ApiEndpoints.PREFIX)
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;
    private final AddressModelAssembler assembler;
    // ============================ GET LIST ADDRESSES =============================
    @GetMapping(ApiEndpoints.ADDRESS_V1)
    public CollectionModel<EntityModel<AddressRes>> getAllAddress(){

        final List<AddressRes> proRes =service.getAllAddress()
                .stream()
                .map(AddressDtoConverter::toResponse)
                .toList();

        final List<EntityModel<AddressRes>> entityModels = proRes
                .stream()
                .map(assembler::toModel)
                .toList();



        return CollectionModel.of(
                entityModels,
                linkTo(methodOn(AddressController.class).getAllAddress())
                        .withSelfRel());
    }

    @GetMapping(ApiEndpoints.ADDRESS_V1 + "/user")
    public CollectionModel<EntityModel<AddressRes>> getAllAddressByUser(
            @RequestHeader("Authorization") String token){

        final List<AddressRes> proRes =service.getAllAddressByUser_Id(token)
                .stream()
                .map(AddressDtoConverter::toResponse)
                .toList();

        final List<EntityModel<AddressRes>> entityModels = proRes
                .stream()
                .map(assembler::toModel)
                .toList();



        return CollectionModel.of(
                entityModels,
                linkTo(methodOn(AddressController.class).getAllAddress())
                        .withSelfRel());
    }

    // ============================ GET ADDRESS BY ID =============================
    @GetMapping(ApiEndpoints.ADDRESS_V1 + "/{id}")
    public EntityModel<AddressRes> getAddressById(@PathVariable(name = "id") Long id){
        return assembler.toModel(
                AddressDtoConverter
                        .toResponse(
                                service.getAddressById(id)));
    }

    // ============================ GET ADDRESSES USER =============================
    @GetMapping(ApiEndpoints.ADDRESS_V1 + "?user={user_id}")
    public EntityModel<AddressRes> getAddressUser(@PathVariable Long user_id){
        return assembler.toModel(
                AddressDtoConverter
                        .toResponse(
                                service.getAddressUser(user_id)));
    }


    // ============================ POST ADDRESS =============================
    @PostMapping(ApiEndpoints.ADDRESS_V1)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createAddress(@RequestBody @Valid CreateAddressReq req,
                                           @RequestHeader("Authorization") String token){
        final EntityModel<AddressRes> entityModel = assembler.toModel(AddressDtoConverter
                .toResponse(service.createAddress(req,token)));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    // ============================ DELETE ADDRESS =============================
    @DeleteMapping(ApiEndpoints.ADDRESS_V1 + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable(name = "id") Long id){
        service.deleteAddress(id);
    }

    // ============================ UPDATE ADDRESS =============================
    @PutMapping(ApiEndpoints.ADDRESS_V1)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateAddress(@RequestBody UpdateAddressReq req){
        final EntityModel<AddressRes> entityModel = assembler
                .toModel(AddressDtoConverter
                        .toResponse(service.updateAddress(req)));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }


}
