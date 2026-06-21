package bg.softuni.eternalbouquet.web;

import bg.softuni.eternalbouquet.model.dto.bouquet.BouquetDto;
import bg.softuni.eternalbouquet.model.dto.reviews.ReviewCreateRequest;
import bg.softuni.eternalbouquet.model.dto.reviews.ReviewDto;
import bg.softuni.eternalbouquet.service.bouquet.BouquetService;
import bg.softuni.eternalbouquet.service.review.ReviewService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class BouquetController {

    private final BouquetService bouquetService;
    private final ReviewService reviewService;

    public BouquetController(BouquetService bouquetService, ReviewService reviewService) {
        this.bouquetService = bouquetService;
        this.reviewService = reviewService;
    }


    @GetMapping("/bouquets/{id}")
    public ModelAndView modelAndView(@PathVariable UUID id){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("bouquet-details");
        modelAndView.addObject("bouquet", bouquetService.getById(id));
        modelAndView.addObject("reviewDto", new ReviewDto());
        modelAndView.addObject("reviews", reviewService.getByBouquetId(id));
        modelAndView.addObject("averageRating", reviewService.getAverageRating(id));

        return modelAndView;
    }

    @GetMapping("/bouquets/edit/{id}")
    public ModelAndView getEditPage(@PathVariable ("id") UUID bouquetId){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bouquet-edit");
        modelAndView.addObject("bouquet", bouquetService.getById(bouquetId));

        return modelAndView;
    }

    @PostMapping("/bouquets/edit/{id}")
    public String editBouquet(@PathVariable ("id") UUID bouquetId,
                              @ModelAttribute BouquetDto bouquetDto){

        bouquetService.update(bouquetId, bouquetDto);

        return "redirect:/bouquets/" + bouquetId;
    }




    
}
