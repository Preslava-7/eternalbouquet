package bg.softuni.eternalbouquet.web;

import bg.softuni.eternalbouquet.model.dto.order.CheckoutRequest;
import bg.softuni.eternalbouquet.model.dto.order.OrderDto;
import bg.softuni.eternalbouquet.model.entity.user.User;
import bg.softuni.eternalbouquet.service.bouquet.BouquetService;
import bg.softuni.eternalbouquet.service.order.OrderService;
import bg.softuni.eternalbouquet.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/cart")
    public ModelAndView getCart(HttpSession session){

        UUID userId = (UUID) session.getAttribute("user_id");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cart");

        modelAndView.addObject("cart", orderService.getCurrentCart(userId));

        return modelAndView;
    }

    @PostMapping("/cart/add/{bouquetId}")
    public String addBouquet(@PathVariable UUID bouquetId,
                                   HttpSession session){

        UUID userId = (UUID) session.getAttribute("user_id");

        orderService.addToCart(userId, bouquetId);

        return "redirect:/cart";
    }

    @PostMapping("/cart/remove/{id}")
    public String removeBouquet(@PathVariable ("id") UUID itemId){
        orderService.removeItem(itemId);

        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public ModelAndView checkoutPage(HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");

        CheckoutRequest checkoutRequest = new CheckoutRequest();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("checkout");
        modelAndView.addObject("cart", orderService.getCurrentCart(userId));
        modelAndView.addObject("checkoutRequest", checkoutRequest);

        return modelAndView;
    }

    @PostMapping("/checkout")
    public String checkout(@ModelAttribute CheckoutRequest request,
                           HttpSession session,
                           RedirectAttributes redirectAttributes){

        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.findEntityById(userId);

        try {
            orderService.checkout(userId, request);
        } catch (RuntimeException ex){
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            return "redirect:/cart";
        }
        session.setAttribute("orders", user.getOrders());

        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public ModelAndView orders(HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orders");
        modelAndView.addObject("orders", orderService.getOrdersByUserId(userId));

        return modelAndView;
    }

    @GetMapping("/orders/{id}")
    public ModelAndView orderDetails(@PathVariable UUID id) {

        ModelAndView modelAndView = new ModelAndView();

        OrderDto order = orderService.getById(id);

        modelAndView.setViewName("order-detail");
        modelAndView.addObject("order", order);

        return modelAndView;
    }


}
