package com.aduca.lms.controller.client;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.aduca.lms.domain.*;
import com.aduca.lms.domain.dto.*;
import com.aduca.lms.service.UserService;

import jakarta.validation.Valid;

@Controller
public class HomePageController {

    private UserService userService;

    public HomePageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "client/auth/login";
        }

        return "redirect:/homepage";

    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String registerPage(Model model,
            @ModelAttribute("registerUser") @Valid RegisterDTO registerUser,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "client/auth/register";
        }

        User newUser = userService.registerDTOtoUser(registerUser);
        newUser.setRole(new Role(3L));
        userService.saveUser(newUser);
        return "client/auth/login";
    }

    @GetMapping("/homepage")
    public String getHomePage() {
        return "client/homepage/show";
    }

}
