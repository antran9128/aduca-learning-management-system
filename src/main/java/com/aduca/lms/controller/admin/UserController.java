package com.aduca.lms.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.aduca.lms.domain.Role;
import com.aduca.lms.domain.User;
import com.aduca.lms.service.UserService;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/user/create")
    public String createNewUser() {
        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setName("An Tran");
        user.setPassword("12345");
        user.setRole(new Role(1L));
        user.setStatus(true);
        user.setUsername("admin001");
        userService.saveUser(user);
        return "admin/dashboard/show";
    }
}
