package com.aduca.lms.controller.frontend;

import com.aduca.lms.service.CategoryService;
import com.aduca.lms.service.CourseService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private CategoryService categoryService;
    private CourseService courseService;

    public HomePageController(UserService userService, CategoryService categoryService, CourseService courseService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.courseService = courseService;
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
        newUser.setCreatedAt(new Date());
        userService.saveUser(newUser);
        return "client/auth/login";
    }

    @GetMapping("/homepage")
    public String getHomePage(Model model) {
        List<Category> categories = categoryService.getCategoriesLimit(6);
        model.addAttribute("categories", categories);
        model.addAttribute("allCategories", categoryService.getAll());
        model.addAttribute("courses", courseService.getCoursesLimit(6));
        model.addAttribute("allCourses", courseService.findAllCourse());
        return "client/homepage/show";
    }




}
