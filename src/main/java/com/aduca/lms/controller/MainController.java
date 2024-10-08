package com.aduca.lms.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/admin/login")
    public String viewAdminLoginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "admin/auth/login";
        }

        return "redirect:/admin";
    }

    // @GetMapping("/login")
    // public String viewLoginPage() {
    // Authentication authentication =
    // SecurityContextHolder.getContext().getAuthentication();
    // if (authentication == null || authentication instanceof
    // AnonymousAuthenticationToken) {
    // return "client/auth/login";
    // }

    // return "redirect:/";
    // }

}
