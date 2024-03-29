package shop.titupet.converter;

import shop.titupet.dtos.user.CreateUserReq;
import shop.titupet.dtos.user.UpdateUserReq;
import shop.titupet.dtos.user.UserRes;
import shop.titupet.models.entities.User;
import shop.titupet.models.enums.ObjectStatus;

public class UserDtoConverter {
    public static User toEntity(CreateUserReq req) {
        User user = User.builder()
                .email(req.getEmail())
                .fullName(req.getFullName())
                .password(req.getPassword())
                .gender(req.isGender())
//                .img(req.getImg())
                .build();
        user.setObjectStatus(ObjectStatus.ACTIVE);
        return user;
    }

    public static User toEntity(UpdateUserReq req) {
        User user = User.builder()
                .fullName(req.getFullName())
                .gender(req.isGender())
                .build();
        user.setObjectStatus(ObjectStatus.ACTIVE);
        return user;
    }

    public static UserRes toResponse(User user) {
        return new UserRes(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.isGender(),
                user.getImg(),
                user.getCreatedAt(),
                user.getCreatedBy(),
                user.getUpdatedAt(),
                user.getUpdatedBy(),
                user.getObjectStatus()
        );
    }
}
