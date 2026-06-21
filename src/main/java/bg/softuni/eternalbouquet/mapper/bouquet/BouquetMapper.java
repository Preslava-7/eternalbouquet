package bg.softuni.eternalbouquet.mapper.bouquet;

import bg.softuni.eternalbouquet.model.dto.bouquet.BouquetDto;
import bg.softuni.eternalbouquet.model.entity.bouquet.Bouquet;
import org.springframework.stereotype.Component;

@Component
public class BouquetMapper {

    public static BouquetDto toDto(Bouquet bouquet){
        if (bouquet == null){
            return null;
        }

        return BouquetDto.builder()
                .id(bouquet.getId())
                .name(bouquet.getName())
                .description(bouquet.getDescription())
                .stock(bouquet.getStock())
                .imageUrl(bouquet.getImageUrl())
                .categories(bouquet.getCategories())
                .size(bouquet.getSize())
                .price(bouquet.getPrice())
                .build();
    }

    public static Bouquet toEntity(BouquetDto bouquetDto){
        if (bouquetDto == null){
            return null;
        }

        return Bouquet.builder()
                .id(bouquetDto.getId())
                .name(bouquetDto.getName())
                .description(bouquetDto.getDescription())
                .imageUrl(bouquetDto.getImageUrl())
                .categories(bouquetDto.getCategories())
                .stock(bouquetDto.getStock())
                .size(bouquetDto.getSize())
                .price(bouquetDto.getPrice())
                .build();
    }
}
