package com.aduca.lms.controller.backend;

import com.aduca.lms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InstructorController {
  private UserService userService;

  public InstructorController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/admin/all/instructor")
  public String getAllContructors(Model model){
    model.addAttribute("instructors", userService.getAllInstructors());
    return "admin/instructor/all_instructor";
  }
}
