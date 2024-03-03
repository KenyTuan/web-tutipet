package shop.titupet.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.titupet.exception.BadRequestException;
import shop.titupet.exception.NotFoundException;
import shop.titupet.models.converter.AddressDtoConverter;
import shop.titupet.models.dtos.address.CreateAddressReq;
import shop.titupet.models.dtos.address.UpdateAddressReq;
import shop.titupet.models.entities.Address;
import shop.titupet.models.entities.User;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.repository.AddressRepo;
import shop.titupet.repository.UserRepository;
import shop.titupet.service.AddressService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepo Addressrepo;

    private final UserRepository userRepo;

    // ============================ SHOW ALL ADDRESS =============================
    @Override
    public List<Address> getAllAddress() {
        return Addressrepo.findAllActive();
    }


    // ============================ FIND ADDRESS BY ID =============================
    @Override
    public Address getAddressById(Long id) {

        return Addressrepo.findById(id)
                .orElseThrow(() -> new NotFoundException("404","Not Found!"));
    }

    // ============================ FIND ADDRESS BY USER ID =============================
    @Override
    public Address getAddressUser(Long userId) {

        return Addressrepo.findActiveByUserID(userId)
                .orElseThrow(() -> new NotFoundException("404","Not Found!"));
    }

    // ============================ Create ADDRESS =============================
    @Override
    @Transactional
    public Address createAddress(CreateAddressReq req) {
        // Find User in db
        final User user = userRepo.findUserActiveById(req.getUser_id())
                .orElseThrow(() -> new NotFoundException("404","Not Found!"));

        try{
            // Change request to entity
            Address address = AddressDtoConverter.toEntity(req);

            // Set User
            address.setUser(user);

            Addressrepo.save(address);

            return address;
        }catch (Exception e){
            throw new BadRequestException("500","Error Server " + e); // return error
        }

    }

    // ============================ DELETE ADDRESS =============================
    @Override
    @Transactional
    public void deleteAddress(Long id) {
        //Find obj in the database
        Address address = Addressrepo.findById(id)
                .orElseThrow(() -> new NotFoundException("404","Not Found!"));
        try {

            // Change Active to Deleted
            address.setObjectStatus(ObjectStatus.DELETED);

            Addressrepo.save(address);
        }catch (Exception e){
            throw new BadRequestException("500","Error Server " + e); // return error
        }

    }


    // ============================ UPDATE ADDRESS =============================
    @Override
    @Transactional
    public Address updateAddress(UpdateAddressReq req) {
        //Find obj in the database
        Address oldAddress = Addressrepo.findActiveById(req.getId())
                .orElseThrow(() -> new NotFoundException("404","Not Found!"));

        try {

            // Update the ObjectStatus field
            oldAddress.setObjectStatus(ObjectStatus.DELETED);

            Addressrepo.save(oldAddress);

            //Create a new Obj
            Address newAddress = AddressDtoConverter.toEntity(req);
            newAddress.setUser(oldAddress.getUser());

            Addressrepo.save(newAddress);

            return newAddress; // Return the updated obj
        }catch (Exception e){
            throw new BadRequestException("500","Error Server " + e); // return error
        }
    }


}
