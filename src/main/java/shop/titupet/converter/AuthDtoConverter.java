package shop.titupet.converter;

import shop.titupet.dtos.auth.AuthRes;
import shop.titupet.dtos.auth.RegisterReq;
import shop.titupet.models.entities.User;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.models.enums.Role;

public class AuthDtoConverter {
    public static AuthRes toResponse(String token, long expTime){
        return new AuthRes(
                token,
                expTime
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
