package bg.softuni.eternalbouquet.mapper.user;

import bg.softuni.eternalbouquet.model.dto.user.UserDto;
import bg.softuni.eternalbouquet.model.dto.user.UserRegisterRequest;
import bg.softuni.eternalbouquet.model.entity.user.User;
import bg.softuni.eternalbouquet.model.entity.user.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDto toDto(User user) {

        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }


    public User toEntity(UserRegisterRequest user) {

        if (user == null) {
            return null;
        }

        return User.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .role(UserRole.USER)
                .build();
    }

}
