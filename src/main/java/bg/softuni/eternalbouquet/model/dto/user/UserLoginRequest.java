package bg.softuni.eternalbouquet.model.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginRequest {
    @NotBlank(message = "Email cannot be empty.")
    @Size(min = 6, message = "Email must be at least 7 characters")
    public String email;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 6, message = "Password must be at least 7 characters")
    public String password;
}
