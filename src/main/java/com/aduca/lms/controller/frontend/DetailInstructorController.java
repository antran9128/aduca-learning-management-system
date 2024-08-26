package com.aduca.lms.controller.frontend;

import com.aduca.lms.domain.User;
import com.aduca.lms.exception.UserNotFoundException;
import com.aduca.lms.service.CourseService;
import com.aduca.lms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DetailInstructorController {
  private UserService userService;
  private CourseService courseService;

  public DetailInstructorController(UserService userService, CourseService courseService) {
    this.userService = userService;
    this.courseService = courseService;
  }

  @GetMapping("/instructor/details/{id}")
  public String InstructorDetails(@PathVariable("id") Long id, Model model) throws UserNotFoundException {
    User instructor = userService.getUserById(id);
    instructor.setJoinedTime(userService.getJoinedTimeRelativeToNow(instructor.getCreatedAt()));
    model.addAttribute("instructor", instructor);
    model.addAttribute("courses", courseService.getCoursesByInstructorAndStatus(instructor));
    return "client/instructor/instructor_details";
  }
}
