package bg.softuni.eternalbouquet.dataSeeder;

import bg.softuni.eternalbouquet.model.entity.bouquet.Bouquet;
import bg.softuni.eternalbouquet.model.entity.bouquet.BouquetSize;
import bg.softuni.eternalbouquet.model.entity.bouquet.Category;
import bg.softuni.eternalbouquet.repository.bouquet.BouquetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataSeeder implements CommandLineRunner {

    private final BouquetRepository bouquetRepository;

    public DataSeeder(BouquetRepository bouquetRepository) {
        this.bouquetRepository = bouquetRepository;
    }

    @Override
    public void run(String... args) {

        if (bouquetRepository.count() == 0) {

            Bouquet b1 = new Bouquet();
            b1.setSize(BouquetSize.SMALL);
            b1.setStock(10);
            b1.setImageUrl("/images/img1.jpeg");
            b1.setDescription("");
            b1.getCategories().add(Category.BIRTHDAY);
            b1.getCategories().add(Category.MOTHER_DAY);
            b1.getCategories().add(Category.GET_WELL);
            b1.setName("Spring bouquet");
            b1.setPrice(BigDecimal.valueOf(17));

            Bouquet b2 = new Bouquet();
            b2.setStock(10);
            b2.setImageUrl("/images/img2.jpeg");
            b2.setDescription("");
            b2.getCategories().add(Category.ANNIVERSARY);
            b2.getCategories().add(Category.VALENTINE);
            b2.setSize(BouquetSize.MEDIUM);
            b2.setName("Peonies");
            b2.setPrice(BigDecimal.valueOf(30));

            Bouquet b3 = new Bouquet();
            b3.setStock(10);
            b3.setImageUrl("/images/img3.jpeg");
            b3.setDescription("");
            b3.getCategories().add(Category.ANNIVERSARY);
            b3.getCategories().add(Category.BIRTHDAY);
            b3.getCategories().add(Category.VALENTINE);
            b3.setSize(BouquetSize.LARGE);
            b3.setName("Beautiful garden");
            b3.setPrice(BigDecimal.valueOf(50));

            Bouquet b4 = new Bouquet();
            b4.setStock(10);
            b4.setImageUrl("/images/img4.jpeg");
            b4.setDescription("");
            b4.getCategories().add(Category.WEDDING);
            b4.getCategories().add(Category.ANNIVERSARY);
            b4.setSize(BouquetSize.SMALL);
            b4.setName("Blue magic");
            b4.setPrice(BigDecimal.valueOf(20));

            Bouquet b5 = new Bouquet();
            b5.setStock(10);
            b5.setImageUrl("/images/img5.jpeg");
            b5.setDescription("");
            b5.getCategories().add(Category.ANNIVERSARY);
            b5.getCategories().add(Category.WEDDING);
            b5.getCategories().add(Category.GET_WELL);
            b5.getCategories().add(Category.VALENTINE);
            b5.getCategories().add(Category.MOTHER_DAY);
            b5.setSize(BouquetSize.MEDIUM);
            b5.setName("Elegant bouquet");
            b5.setPrice(BigDecimal.valueOf(22));

            Bouquet b6 = new Bouquet();
            b6.setStock(10);
            b6.setImageUrl("/images/img6.jpeg");
            b6.setDescription("");
            b6.getCategories().add(Category.MOTHER_DAY);
            b6.getCategories().add(Category.VALENTINE);
            b6.getCategories().add(Category.BIRTHDAY);
            b6.getCategories().add(Category.GET_WELL);
            b6.setSize(BouquetSize.SMALL);
            b6.setName("Colorful tulips");
            b6.setPrice(BigDecimal.valueOf(18));

            Bouquet b7 = new Bouquet();
            b7.setStock(10);
            b7.setImageUrl("/images/img7.jpeg");
            b7.setDescription("");
            b7.getCategories().add(Category.WEDDING);
            b7.getCategories().add(Category.ANNIVERSARY);
            b7.getCategories().add(Category.VALENTINE);
            b7.getCategories().add(Category.BIRTHDAY);
            b7.setSize(BouquetSize.MEDIUM);
            b7.setName("Pink magic");
            b7.setPrice(BigDecimal.valueOf(23));

            Bouquet b8 = new Bouquet();
            b8.setStock(10);
            b8.setImageUrl("/images/img8.jpeg");
            b8.setDescription("");
            b8.getCategories().add(Category.BIRTHDAY);
            b8.getCategories().add(Category.GET_WELL);
            b8.getCategories().add(Category.MOTHER_DAY);
            b8.getCategories().add(Category.VALENTINE);
            b8.setSize(BouquetSize.SMALL);
            b8.setName("Spring bloom");
            b8.setPrice(BigDecimal.valueOf(18));

            Bouquet b9 = new Bouquet();
            b9.setStock(10);
            b9.setImageUrl("/images/img9.jpeg");
            b9.setDescription("");
            b9.getCategories().add(Category.ANNIVERSARY);
            b9.getCategories().add(Category.VALENTINE);
            b9.setSize(BouquetSize.LARGE);
            b9.setName("Lucky girl");
            b9.setPrice(BigDecimal.valueOf(30));

            bouquetRepository.save(b1);
            bouquetRepository.save(b2);
            bouquetRepository.save(b3);
            bouquetRepository.save(b4);
            bouquetRepository.save(b5);
            bouquetRepository.save(b6);
            bouquetRepository.save(b7);
            bouquetRepository.save(b8);
            bouquetRepository.save(b9);
        }
    }
}