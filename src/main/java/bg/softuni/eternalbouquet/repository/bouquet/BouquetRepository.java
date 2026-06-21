package bg.softuni.eternalbouquet.repository.bouquet;

import bg.softuni.eternalbouquet.model.entity.bouquet.Bouquet;
import bg.softuni.eternalbouquet.model.entity.bouquet.BouquetSize;
import bg.softuni.eternalbouquet.model.entity.bouquet.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BouquetRepository extends JpaRepository<Bouquet, UUID> {

    List<Bouquet> findAllByCategoriesContaining(Category category);

    List<Bouquet> findBySize(BouquetSize size);

    List<Bouquet> findByNameContainingIgnoreCase(String name);
}
