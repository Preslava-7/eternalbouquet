package bg.softuni.eternalbouquet.web;

import bg.softuni.eternalbouquet.model.dto.user.UserDto;
import bg.softuni.eternalbouquet.model.dto.user.UserLoginRequest;
import bg.softuni.eternalbouquet.model.dto.user.UserRegisterRequest;
import bg.softuni.eternalbouquet.model.entity.user.UserRole;
import bg.softuni.eternalbouquet.service.bouquet.BouquetService;
import bg.softuni.eternalbouquet.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();

        UserLoginRequest userLoginRequest = UserLoginRequest.builder().build();

        modelAndView.setViewName("login");
        modelAndView.addObject("userLoginRequest", userLoginRequest);

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid UserLoginRequest userLoginRequest,
                        BindingResult bindingResult,
                        HttpSession session){

        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("userLoginRequest", userLoginRequest);
            return modelAndView;
        }

            UserDto user = userService.login(userLoginRequest);
            session.setAttribute("user_id", user.getId());
            session.setAttribute("username", user.getUsername());

            session.setAttribute("isAdmin", user.getRole() == UserRole.ADMIN);

            return new ModelAndView("redirect:/index");


    }


    @GetMapping("/register")
    public ModelAndView getRegister(){

        UserRegisterRequest userRegisterRequest = UserRegisterRequest.builder().build();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        modelAndView.addObject("userRegisterRequest", userRegisterRequest);

        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute UserRegisterRequest userRegisterRequest,
                                 BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("register");
            modelAndView.addObject("userRegisterRequest", userRegisterRequest);

            return modelAndView;
        }

        userService.register(userRegisterRequest);

        return new ModelAndView("redirect:/login");

    }

    @GetMapping("/admin/users")
    public ModelAndView getUsers(){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("users", userService.getAllUsers());

        modelAndView.setViewName("users");

        return modelAndView;

    }

    @PostMapping("/admin/users/{id}/role")
    public String updateRole(@PathVariable ("id") UUID userId) {

        userService.switchRole(userId);

        return "redirect:/admin/users";
    }
}

