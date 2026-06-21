package bg.softuni.eternalbouquet.model.dto.reviews;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewCreateRequest {
    private Integer rating;

    private String comment;
}
