package bg.softuni.eternalbouquet.model.dto.bouquet;

import bg.softuni.eternalbouquet.model.entity.bouquet.BouquetSize;
import bg.softuni.eternalbouquet.model.entity.bouquet.Category;
import bg.softuni.eternalbouquet.model.entity.reviews.Review;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class BouquetDto {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private Integer stock;
    private BouquetSize size;
    private Set<Category> categories;
    private List<Review> reviews = new ArrayList<>();
}
