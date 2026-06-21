package bg.softuni.eternalbouquet.mapper.review;

import bg.softuni.eternalbouquet.mapper.user.UserMapper;
import bg.softuni.eternalbouquet.model.dto.reviews.ReviewDto;
import bg.softuni.eternalbouquet.model.entity.reviews.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewMapper {

    public static ReviewDto toDto(Review review) {

        if (review == null) {
            return null;
        }

        return ReviewDto.builder()
                .id(review.getId())
                .rating(review.getRating())
                .comment(review.getComment())
                .createdOn(review.getCreatedOn())
                .user(UserMapper.toDto(review.getUser()))
                .build();
    }

}
