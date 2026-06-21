
package bg.softuni.eternalbouquet.service.bouquet;

import bg.softuni.eternalbouquet.mapper.bouquet.BouquetMapper;
import bg.softuni.eternalbouquet.model.dto.bouquet.BouquetDto;
import bg.softuni.eternalbouquet.model.entity.bouquet.Bouquet;
import bg.softuni.eternalbouquet.model.entity.bouquet.BouquetSize;
import bg.softuni.eternalbouquet.model.entity.bouquet.Category;
import bg.softuni.eternalbouquet.model.entity.reviews.Review;
import bg.softuni.eternalbouquet.repository.bouquet.BouquetRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BouquetService {
    private final BouquetRepository bouquetRepository;

    public BouquetService(BouquetRepository bouquetRepository) {
        this.bouquetRepository = bouquetRepository;
    }

    public List<BouquetDto> getAll() {
        return bouquetRepository.findAll()
                .stream()
                .map(BouquetMapper::toDto)
                .toList();
    }

    public BouquetDto getById(UUID id){
        Bouquet bouquet = bouquetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bouquet not found"));

        return BouquetMapper.toDto(bouquet);
    }


    public List<BouquetDto> getByOccasion(Category occasion){

        return bouquetRepository.findAllByCategoriesContaining(occasion)
                .stream()
                .map(BouquetMapper::toDto)
                .toList();
    }

    public List<BouquetDto> getBySize(BouquetSize size){

        return bouquetRepository.findBySize(size)
                .stream()
                .map(BouquetMapper::toDto)
                .toList();
    }


    @Transactional
    public void update(UUID bouquetId, BouquetDto bouquetDto) {

        Bouquet bouquet = bouquetRepository.findById(bouquetId)
                .orElseThrow(() -> new RuntimeException("Bouquet not found"));

        bouquet.setName(bouquetDto.getName());
        bouquet.setPrice(bouquetDto.getPrice());
        bouquet.setImageUrl(bouquetDto.getImageUrl());
        bouquet.setDescription(bouquetDto.getDescription());
        bouquet.setStock(bouquetDto.getStock());
        bouquet.setCategories(bouquetDto.getCategories());

        bouquetRepository.save(bouquet);
    }

}
