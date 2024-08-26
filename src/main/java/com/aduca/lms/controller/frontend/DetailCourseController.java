package com.aduca.lms.controller.frontend;

import com.aduca.lms.domain.Category;
import com.aduca.lms.domain.Course;
import com.aduca.lms.domain.User;
import com.aduca.lms.service.CategoryService;
import com.aduca.lms.service.CourseService;
import com.aduca.lms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DetailCourseController {
  private CourseService courseService;
  private UserService userService;
  private CategoryService catService;
  public DetailCourseController(CourseService courseService, UserService userService, CategoryService catService) {
    this.courseService = courseService;
    this.userService = userService;
    this.catService = catService;
  }

  @GetMapping("/course/details/{id}/{slug}")
  public String getDetailPage(@PathVariable("id") Long id, @PathVariable("slug") String slug, Model model){
    Course course = courseService.getById(id);
    User user = course.getInstructor();
    user.setJoinedTime(userService.getJoinedTimeRelativeToNow(user.getCreatedAt()));
    course.setInstructor(user);

    model.addAttribute("categories", catService.getAll());
    model.addAttribute("relatedCourses", courseService.getAll(user.getId()));
    model.addAttribute("course", course);
    model.addAttribute("suggestedCourses", course.getCategory().getCourses());
    return "client/course/course_detail";
  }
}
