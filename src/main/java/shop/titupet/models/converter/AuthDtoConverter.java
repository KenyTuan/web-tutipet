package shop.titupet.models.converter;

import shop.titupet.models.dtos.address.AddressRes;
import shop.titupet.models.dtos.auth.AuthReq;
import shop.titupet.models.dtos.auth.AuthRes;
import shop.titupet.models.dtos.auth.RegisterReq;
import shop.titupet.models.entities.Address;
import shop.titupet.models.entities.User;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.models.enums.Role;

public class AuthDtoConverter {
    public static AuthRes toResponse(String token){
        return new AuthRes(
                token
        );
    }

    public static User toEntity(RegisterReq req){
        User user = User
                    .builder()
                    .fullName(req.getFullName())
                    .email(req.getEmail())
                    .password(req.getPassword())
                    .gender(req.isGender())
                    .role(Role.USER)
                    .build();
        user.setObjectStatus(ObjectStatus.ACTIVE);
        return user;
    }


}
