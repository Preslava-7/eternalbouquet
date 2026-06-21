package bg.softuni.eternalbouquet.model.entity.bouquet;

import jakarta.persistence.*;
import lombok.*;
import bg.softuni.eternalbouquet.model.entity.reviews.Review;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "bouquets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @ElementCollection(targetClass = Category.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "categories",
            joinColumns = @JoinColumn(name = "bouquet_id")
    )
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "bouquet", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();


}
