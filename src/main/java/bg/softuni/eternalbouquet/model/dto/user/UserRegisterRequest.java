package bg.softuni.eternalbouquet.model.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterRequest {
    @NotBlank(message = "Email cannot be empty.")
    @NotNull(message = "Email must be at least 7 characters")
    private String email;

    @NotBlank(message = "Username cannot be empty.")
    @Size(min = 6, message = "Username must be at least 7 characters")
    private String username;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 6, message = "Password must be at least 7 characters")
    private String password;
}
