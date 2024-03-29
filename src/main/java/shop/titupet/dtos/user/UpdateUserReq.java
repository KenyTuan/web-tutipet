package shop.titupet.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateUserReq {

    @NotBlank(message = "Name is required.")
    @Size(min = 5, max = 255, message = "Name length ranges from 5 to 255 characters")
    private String fullName;

    private boolean gender;

//    private String img;

}
