package bg.softuni.eternalbouquet.web;

import bg.softuni.eternalbouquet.model.dto.bouquet.BouquetDto;
import bg.softuni.eternalbouquet.model.entity.bouquet.BouquetSize;
import bg.softuni.eternalbouquet.model.entity.bouquet.Category;
import bg.softuni.eternalbouquet.service.bouquet.BouquetService;
import bg.softuni.eternalbouquet.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {
    private final BouquetService bouquetService;

    public IndexController(BouquetService bouquetService) {
        this.bouquetService = bouquetService;
    }

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("bouquets", bouquetService.getAll());
        return modelAndView;
    }

    @GetMapping("/index")
    public ModelAndView home(HttpSession session,
                             @RequestParam(required = false) Category category,
                             @RequestParam(required = false) BouquetSize size) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        List<BouquetDto> bouquets;

        if (size != null) {
            bouquets = bouquetService.getBySize(size);
        } else if (category != null) {
            bouquets = bouquetService.getByOccasion(category);
        } else {
            bouquets = bouquetService.getAll();
        }

        modelAndView.addObject("bouquets", bouquets);
        modelAndView.addObject("selectedCategory", category);
        modelAndView.addObject("selectedSize", bouquetService.getBySize(size));

        return modelAndView;
    }

    @GetMapping("/signout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/";
    }






}
