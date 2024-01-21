package shop.titupet.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.models.converter.AddressDtoConverter;
import shop.titupet.models.dtos.address.AddressRes;
import shop.titupet.models.dtos.address.CreateAddressReq;
import shop.titupet.models.dtos.address.UpdateAddressReq;
import shop.titupet.models.entities.Address;
import shop.titupet.service.AddressService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiEndpoints.PREFIX)
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;

    // ============================ GET LIST ADDRESSES =============================
    @GetMapping(ApiEndpoints.ADDRESS_V1)
    public List<AddressRes> getAllAddress(){
        return service.getAllAddress().stream().map(AddressDtoConverter::toResponse).collect(Collectors.toList());
    }

    // ============================ GET ADDRESS BY ID =============================
    @GetMapping(ApiEndpoints.ADDRESS_V1 + "/{id}")
    public AddressRes getAddressById(@PathVariable(name = "id") Long id){
        return AddressDtoConverter.toResponse(service.getAddressById(id));
    }

    // ============================ GET ADDRESSES USER =============================
    @GetMapping(ApiEndpoints.ADDRESS_V1 + "?user={user_id}")
    public AddressRes getAddressUser(@PathVariable Long user_id){
        return AddressDtoConverter.toResponse(service.getAddressUser(user_id));
    }


    // ============================ POST ADDRESS =============================
    @PostMapping(ApiEndpoints.ADDRESS_V1)
    @ResponseStatus(HttpStatus.CREATED)
    public AddressRes createAddress(@RequestBody @Valid CreateAddressReq req){
        return AddressDtoConverter.toResponse(service.createAddress(req));
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
    public AddressRes updateAddress(@RequestBody UpdateAddressReq req){
        return AddressDtoConverter.toResponse( service.updateAddress(req));
    }


}
