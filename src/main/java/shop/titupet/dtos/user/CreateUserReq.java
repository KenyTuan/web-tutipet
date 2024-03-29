package shop.titupet.dtos.user;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUserReq {

    @Size(min = 5, max = 255, message = "Name length ranges from 5 to 255 characters")
    @NotBlank(message = "Name is required.")
    private String fullName;

    private boolean gender;

    @Email(message = "Invalid Email!")
    private String email;

    @NotBlank(message = "Password is required.")
    private String password;

    @NotBlank(message = "Password Confirm is required.")
    private String confirmPassword;

//    @NotNull(message = "Role is required.")
//    private Role role;
}
