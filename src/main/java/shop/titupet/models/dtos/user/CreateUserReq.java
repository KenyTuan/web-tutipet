package shop.titupet.models.dtos.user;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUserReq {

    @Size(min = 5, max = 255, message = "Name length ranges from 5 to 255 characters")
    @NotBlank(message = "Name is required.")
    private String fullName;

    @Email(message = "Invalid Email!")
    private String email;

    @Min(value = 5,message = "Price must greater than 5")
    @NotBlank(message = "Password is required.")
    private String password;

    @NotNull(message = "Gender is required.")
    private boolean gender;

    private String img;

//    @NotNull(message = "Role is required.")
//    private Role role;
}
