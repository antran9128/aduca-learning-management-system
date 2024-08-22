package com.aduca.lms.controller.backend;

import com.aduca.lms.domain.Course;
import com.aduca.lms.domain.SubCategory;
import com.aduca.lms.service.CategoryService;
import com.aduca.lms.service.CourseService;
import com.aduca.lms.service.SubCategoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CourseController {
  private CourseService courseService;
  private CategoryService categoryService;
  private SubCategoryService subCategoryService;

  public CourseController(CourseService courseService, CategoryService categoryService, SubCategoryService subCategoryService) {
    this.courseService = courseService;
    this.categoryService = categoryService;
    this.subCategoryService = subCategoryService;
  }

  @GetMapping("/instructor/all/course")
  public String getAllCoursesPage(HttpServletRequest request, Model model){
    HttpSession session = request.getSession(false);
    Long id = (Long) session.getAttribute("id");

    model.addAttribute("courses", courseService.getAll(id));
    return "instructor/course/all_courses";
  }

  @GetMapping("instructor/add/course")
  public String getAddCoursePage(Model model){
    model.addAttribute("course", new Course());
    model.addAttribute("categories", categoryService.getAll());
    return "instructor/course/add_courses";
  }

  @GetMapping("/subcategory/ajax/{id}")
  @ResponseBody
  public List<SubCategory> getSubCategories(@PathVariable("id") Long id){
      List<SubCategory> list = subCategoryService.findByCategory_Id(id);
      return list;
  }

}
