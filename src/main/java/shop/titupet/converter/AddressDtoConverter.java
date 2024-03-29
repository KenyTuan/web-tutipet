package shop.titupet.converter;

import shop.titupet.dtos.address.AddressRes;
import shop.titupet.dtos.address.CreateAddressReq;
import shop.titupet.dtos.address.UpdateAddressReq;
import shop.titupet.models.entities.Address;
import shop.titupet.models.enums.ObjectStatus;

public class AddressDtoConverter {

    public static Address toEntity(CreateAddressReq req){
        Address address = Address.builder()
                .address(req.getAddress())
                .phone(req.getPhone())
                .receiverName(req.getReceiverName())
                .build();
        address.setObjectStatus(ObjectStatus.ACTIVE);
        return address;
    }

    public static Address toEntity(UpdateAddressReq req){
        Address address = Address.builder()
                .address(req.getAddress())
                .phone(req.getPhone())
                .receiverName(req.getReceiverName())
                .build();
        address.setObjectStatus(ObjectStatus.ACTIVE);
        return address;
    }

    public static AddressRes toResponse(Address address){
        return new AddressRes(
                address.getId(),
                address.getReceiverName(),
                address.getAddress(),
                address.getPhone(),
                address.getCreatedAt(),
                address.getCreatedBy(),
                address.getUpdatedAt(),
                address.getUpdatedBy(),
                address.getObjectStatus()
        );
    }

}
