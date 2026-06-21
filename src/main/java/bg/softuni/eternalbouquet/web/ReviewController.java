package bg.softuni.eternalbouquet.web;

import bg.softuni.eternalbouquet.model.dto.reviews.ReviewDto;
import bg.softuni.eternalbouquet.service.review.ReviewService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/bouquets/{id}/reviews/add")
    public String addReview(@PathVariable("id") UUID bouquetId,
                                  @Valid ReviewDto reviewDto,
                                  BindingResult bindingResult,
                                  HttpSession session,
                                  RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "redirect:/bouquets/" + bouquetId;
        }

        UUID userId = (UUID) session.getAttribute("user_id");

        if (userId == null) {
            return "redirect:/login";
        }

        if(reviewService.existsByUserIdAndBouquetId(userId, bouquetId)){
            redirectAttributes.addFlashAttribute("error",
                    "You already reviewed this bouquet!");
            return "redirect:/bouquets/" + bouquetId;
        }

        reviewService.create(
                bouquetId,
                userId,
                reviewDto.getComment(),
                reviewDto.getRating()
        );

        redirectAttributes.addFlashAttribute("success",
                "Review added successfully!");

        return "redirect:/bouquets/" + bouquetId;
    }
}
