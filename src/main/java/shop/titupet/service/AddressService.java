package shop.titupet.service;

import shop.titupet.dtos.address.CreateAddressReq;
import shop.titupet.dtos.address.UpdateAddressReq;
import shop.titupet.models.entities.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAllAddress();

    Address getAddressById(Long id);

    Address createAddress(CreateAddressReq req,String token);

    void deleteAddress(Long id);

    Address updateAddress(UpdateAddressReq req);


    Address getAddressUser(Long userId);

    List<Address> getAllAddressByUser_Id(String token);
}
