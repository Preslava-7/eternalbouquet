package bg.softuni.eternalbouquet.model.entity.reviews;

import bg.softuni.eternalbouquet.model.entity.bouquet.Bouquet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import bg.softuni.eternalbouquet.model.entity.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer rating;

    @Column(columnDefinition = "TEXT")
    private String comment;

    private LocalDateTime createdOn;

    @ManyToOne
    private User user;

    @ManyToOne
    private Bouquet bouquet;

    @PrePersist
    public void prePersist() {
        this.createdOn = LocalDateTime.now();
    }
}
