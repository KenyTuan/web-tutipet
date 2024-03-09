package shop.titupet.service.Impl;

import io.jsonwebtoken.Claims;
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
import shop.titupet.security.JwtService;
import shop.titupet.service.AddressService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepo Addressrepo;

    private final UserRepository userRepo;
    private final JwtService jwtService;
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

    @Override
    public List<Address> getAllAddressByUser_Id(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new BadRequestException("400", "Invalid or missing Authorization header");
        }

        final Claims claims = jwtService.decodeToken(token.substring(7));

        final Long userId = ((Number) claims.get("userId")).longValue();

        return Addressrepo.findAllActiveByUser_ID(userId);
    }

    // ============================ Create ADDRESS =============================
    @Override
    public Address createAddress(CreateAddressReq req, String tokenReq) {

        try{
            if (tokenReq == null || !tokenReq.startsWith("Bearer ")) {
                throw new BadRequestException("400", "Invalid or missing Authorization header");
            }

            final Claims claims = jwtService
                    .decodeToken(tokenReq.substring(7));

            final Long userId = ((Number) claims.get("userId")).longValue();

            final User user = userRepo.findUserActiveById(userId)
                    .orElseThrow(() -> new NotFoundException("404","Not Found!"));

            final Address address = AddressDtoConverter.toEntity(req);

            address.setUser(user);

            Addressrepo.save(address);

            return address;
    }catch (Exception e){
            throw new BadRequestException("500","Error Server " + e); // return error
        }

    }

    // ============================ DELETE ADDRESS =============================
    @Override
    public void deleteAddress(Long id) {
        Address address = Addressrepo.findActiveById(id)
                .orElseThrow(() -> new NotFoundException("404","Not Found!"));
        try {
            address.setObjectStatus(ObjectStatus.DELETED);
            Addressrepo.save(address);

        }catch (Exception e){
            throw new BadRequestException("500","Error Server " + e); // return error
        }

    }


    // ============================ UPDATE ADDRESS =============================
    @Override
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
