package bg.softuni.eternalbouquet.model.dto.user;

import bg.softuni.eternalbouquet.model.entity.bouquet.Bouquet;
import bg.softuni.eternalbouquet.model.entity.order.Order;
import bg.softuni.eternalbouquet.model.entity.user.UserRole;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserDto {
    private UUID id;
    private String username;
    private String email;
    private String password;
    private UserRole role;
    private List<Order> orders = new ArrayList<>();
    private List<Bouquet> favouriteBouquets = new ArrayList<>();
}
