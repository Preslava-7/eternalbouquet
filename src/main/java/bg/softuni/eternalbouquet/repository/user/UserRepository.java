<<<<<<< HEAD
package bg.softuni.eternalbouquet.repository.user;

import bg.softuni.eternalbouquet.model.entity.bouquet.Bouquet;
import bg.softuni.eternalbouquet.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User findEntityById(UUID userId);

=======
package bg.softuni.eternalbouquet.repository.user;public class UserRepository {
>>>>>>> 6069ab8 (feat: add domain entities repositories and service layer)
}
