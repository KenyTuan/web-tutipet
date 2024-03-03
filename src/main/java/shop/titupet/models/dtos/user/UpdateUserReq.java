package shop.titupet.models.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateUserReq {

    @NotBlank(message = "ID is required")
    private Long id;

    @Size(min = 5, max = 255, message = "Name length ranges from 5 to 255 characters")
    @NotBlank(message = "Name is required.")
    private String fullName;

    private boolean gender;

    private String img;

}
