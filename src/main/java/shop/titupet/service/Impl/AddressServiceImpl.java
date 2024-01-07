package shop.titupet.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.titupet.config.exception.NotFoundException;
import shop.titupet.models.converter.AddressDtoConverter;
import shop.titupet.models.dtos.address.CreateAddressReq;
import shop.titupet.models.dtos.address.UpdateAddressReq;
import shop.titupet.models.entities.Address;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.repository.AddressRepo;
import shop.titupet.service.AddressService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepo repo;


    @Override
    public List<Address> getAllAddress() {
        return repo.findAll();
    }

    @Override
    public Address getAddressById(Long id) {

        return repo.findById(id).orElseThrow(() -> new NotFoundException("404","Address Not Found!"));
    }

    @Override
    public Address createAddress(CreateAddressReq req) {
        Address address = AddressDtoConverter.toEntity(req);

        repo.save(address);
        return address;
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = repo.findById(id).orElseThrow(() -> new NotFoundException("404","Address Not Found."));

        address.setObjectStatus(ObjectStatus.DELETED);
        repo.save(address);
    }

    @Override
    public Address updateAddress(UpdateAddressReq req) {
        Address oldAddress = repo.findById(req.getId()).orElseThrow(() -> new NotFoundException("404","Address Not Found."));

        oldAddress.setObjectStatus(ObjectStatus.DELETED);
        repo.save(oldAddress);

        Address newAddress = AddressDtoConverter.toEntity(req);

        repo.save(newAddress);
        return newAddress;
    }
}
