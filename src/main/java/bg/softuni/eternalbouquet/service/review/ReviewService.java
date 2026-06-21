<<<<<<< HEAD
package bg.softuni.eternalbouquet.service.review;

import bg.softuni.eternalbouquet.mapper.review.ReviewMapper;
import bg.softuni.eternalbouquet.model.dto.reviews.ReviewDto;
import bg.softuni.eternalbouquet.model.entity.bouquet.Bouquet;
import bg.softuni.eternalbouquet.model.entity.reviews.Review;
import bg.softuni.eternalbouquet.model.entity.user.User;
import bg.softuni.eternalbouquet.repository.bouquet.BouquetRepository;
import bg.softuni.eternalbouquet.repository.review.ReviewRepository;
import bg.softuni.eternalbouquet.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BouquetRepository bouquetRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, BouquetRepository bouquetRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.bouquetRepository = bouquetRepository;
        this.userRepository = userRepository;
    }


    public ReviewDto create(UUID bouquetId,
                            UUID userId,
                            String comment,
                            Integer rating) {

        Bouquet bouquet = bouquetRepository.findById(bouquetId)
                .orElseThrow(() -> new RuntimeException("Bouquet not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(reviewRepository.existsByUserIdAndBouquetId(userId, bouquetId)){
            throw new RuntimeException("Already reviewed!");
        }

        Review review = new Review();

        review.setBouquet(bouquet);
        review.setUser(user);
        review.setCreatedOn(LocalDateTime.now());
        review.setRating(rating);
        review.setComment(comment);

        return ReviewMapper.toDto(reviewRepository.save(review));
    }

    public List<ReviewDto> getByBouquetId(UUID bouquetId){
        return reviewRepository.findAllByBouquetId(bouquetId)
                .stream()
                .map(ReviewMapper::toDto)
                .toList();
    }

    public ReviewDto update(UUID id,
                            String comment,
                            Integer rating){

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setComment(comment);
        review.setRating(rating);

        return ReviewMapper.toDto(reviewRepository.save(review));

    }

    public void delete(UUID id){
        reviewRepository.deleteById(id);
    }

    public double getAverageRating(UUID bouquetId) {
        List<Review> reviews = reviewRepository.findAllByBouquetId(bouquetId);

        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }

    public boolean existsByUserIdAndBouquetId(UUID userId, UUID bouquetId) {
        return reviewRepository.existsByUserIdAndBouquetId(userId, bouquetId);
    }
}











=======
package bg.softuni.eternalbouquet.service.review;public class ReviewService {
}
>>>>>>> 6069ab8 (feat: add domain entities repositories and service layer)
