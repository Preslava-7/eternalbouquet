<<<<<<< HEAD
package bg.softuni.eternalbouquet.repository.order;

import bg.softuni.eternalbouquet.model.entity.order.Order;
import bg.softuni.eternalbouquet.model.entity.order.OrderStatus;
import bg.softuni.eternalbouquet.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByUserOrderByCreatedOnDesc(User user);

    Optional<Order> findByUserAndStatus(User user, OrderStatus status);

    Optional<Order> findByUserIdAndStatus(UUID userId, OrderStatus status);

=======
package bg.softuni.eternalbouquet.repository.order;public interface OrderRepository {
>>>>>>> 6069ab8 (feat: add domain entities repositories and service layer)
}
