package bg.softuni.eternalbouquet.repository.review;

import bg.softuni.eternalbouquet.model.entity.bouquet.Bouquet;
import bg.softuni.eternalbouquet.model.entity.reviews.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findAllByBouquetId(UUID bouquetId);

    boolean existsByUserIdAndBouquetId(UUID userId, UUID bouquetId);
}
