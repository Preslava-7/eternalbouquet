package bg.softuni.eternalbouquet.model.entity.user;

import bg.softuni.eternalbouquet.model.entity.bouquet.Bouquet;
import jakarta.persistence.*;
import lombok.*;
import bg.softuni.eternalbouquet.model.entity.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_favourite_bouquets",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "bouquet_id")
    )
    private List<Bouquet> favouriteBouquets = new ArrayList<>();

}
