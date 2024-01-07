package shop.titupet.models.converter;

import shop.titupet.models.dtos.user.CreateUserReq;
import shop.titupet.models.dtos.user.UserRes;
import shop.titupet.models.entities.User;
import shop.titupet.models.enums.ObjectStatus;

public class UserDtoConverter {
    public static User toEntity(CreateUserReq req) {
        User user = User.builder()
                .email(req.getEmail())
                .fullName(req.getFullName())
                .gender(req.isGender())
                .img(req.getImg())
                .build();
        user.setObjectStatus(ObjectStatus.ACTIVE);

        return user;
    };

    public static UserRes toResponse(User user) {
        return new UserRes(
                user.getId(),
                user.getEmail(),
                user.isGender(),
                user.getImg()
        );
    }
}
