package bg.softuni.eternalbouquet.model.dto.reviews;

import bg.softuni.eternalbouquet.model.dto.bouquet.BouquetDto;
import bg.softuni.eternalbouquet.model.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private UUID id;
    private Integer rating;
    private String comment;
    private LocalDateTime createdOn;
    private UserDto user;
    private BouquetDto bouquet;
}
