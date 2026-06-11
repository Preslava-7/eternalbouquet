package bg.softuni.eternalbouquet.model.entity.bouquet;

import jakarta.persistence.*;
import lombok.*;
import bg.softuni.eternalbouquet.model.entity.reviews.Review;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "bouquets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bouquet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Integer stock;

    @Enumerated(EnumType.STRING)
    private BouquetSize size;

    @Enumerated(EnumType.STRING)
    private OccasionType occasion;

    @OneToMany(mappedBy = "bouquet", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();


}
