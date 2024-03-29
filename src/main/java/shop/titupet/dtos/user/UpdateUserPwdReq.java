package shop.titupet.dtos.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateUserPwdReq {


    @NotBlank(message = "Old Password is required.")
    private String oldPassword;

    @NotBlank(message = "New Password is required.")
    private String newPassword;

    @NotBlank(message = "New Password Confirm is required.")
    private String confirmPassword;

}
