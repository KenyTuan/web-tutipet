package shop.titupet.service;

import shop.titupet.models.dtos.address.CreateAddressReq;
import shop.titupet.models.dtos.address.UpdateAddressReq;
import shop.titupet.models.dtos.user.CreateUserReq;
import shop.titupet.models.dtos.user.UserRes;
import shop.titupet.models.entities.Address;
import shop.titupet.models.entities.User;

import java.util.Collection;
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
