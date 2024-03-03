package shop.titupet.models.dtos.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthReq {

    private String email;

    private String password;


}
