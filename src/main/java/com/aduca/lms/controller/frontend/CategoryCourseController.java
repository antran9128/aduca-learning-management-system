package com.aduca.lms.controller.frontend;

import com.aduca.lms.domain.Category;
import com.aduca.lms.domain.Course;
import com.aduca.lms.domain.SubCategory;
import com.aduca.lms.service.CategoryService;
import com.aduca.lms.service.CourseService;
import com.aduca.lms.service.SubCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CategoryCourseController {
  private CategoryService categoryService;
  private SubCategoryService subCategoryService;
  private CourseService courseService;
  public CategoryCourseController(CategoryService categoryService, CourseService courseService, SubCategoryService subCategoryService) {
    this.categoryService = categoryService;
    this.courseService = courseService;
    this.subCategoryService = subCategoryService;
  }

  @GetMapping("/category/{id}/{slug}")
  public String CategoryCourse(@PathVariable("id") Long id, @PathVariable("slug") String slug, Model model){
    Category category = categoryService.getCategoryById(id);
    List<Course> courses = courseService.getCoursesByCategoryAndStatus(category);
    model.addAttribute("category", category);
    model.addAttribute("courses", courses);
    return "client/course/category_course";

  }

  @GetMapping("/subcategory/{id}/{slug}")
  public String SubCategoryCourse(@PathVariable("id") Long id, @PathVariable("slug") String slug, Model model){
    SubCategory subcategory = subCategoryService.getSubCategoryById(id);
    List<Course> courses = courseService.getCoursesBySubCategoryAndStatus(subcategory);
    model.addAttribute("subcategory", subcategory);
    model.addAttribute("courses", courses);
    return "client/course/subcategory_course";

  }
}
