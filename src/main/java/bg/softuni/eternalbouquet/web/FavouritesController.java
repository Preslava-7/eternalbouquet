package bg.softuni.eternalbouquet.web;

import bg.softuni.eternalbouquet.service.bouquet.BouquetService;
import bg.softuni.eternalbouquet.service.order.OrderService;
import bg.softuni.eternalbouquet.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("favorites")
public class FavouritesController {

    private final BouquetService bouquetService;
    private final UserService userService;
    private final OrderService orderService;

    public FavouritesController(BouquetService bouquetService, UserService userService, OrderService orderService) {
        this.bouquetService = bouquetService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping()
    public ModelAndView getFavorites(HttpSession session){

        UUID userId = (UUID) session.getAttribute("user_id");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("favorites");
        modelAndView.addObject("favoriteBouquets", userService.getFavouriteBouquets(userId));

        return modelAndView;
    }

    @PostMapping("/add/{id}")
    public String addToFavorites(@PathVariable ("id") UUID bouquetId,
                                 HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");

        userService.addToFavourites(userId, bouquetId);

        return "redirect:/";
    }

    @PostMapping("/remove/{id}")
    public String removeFromFavourites(@PathVariable ("id") UUID bouquetId,
                                 HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");

        userService.removeFromFavourites(userId, bouquetId);

        return "redirect:/favorites";
    }

    @PostMapping("/cart/add/{bouquetId}")
    public String addBouquet(@PathVariable UUID bouquetId,
                             HttpSession session){

        UUID userId = (UUID) session.getAttribute("user_id");

        orderService.addToCart(userId, bouquetId);

        return "redirect:/cart";
    }
}
