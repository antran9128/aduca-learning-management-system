package com.aduca.lms.controller.backend;

import com.aduca.lms.domain.Course;
import com.aduca.lms.domain.CourseSection;
import com.aduca.lms.service.CourseSectionService;
import com.aduca.lms.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
public class CourseSectionController {
  private CourseService courseService;
  private CourseSectionService courseSectionService;

  public CourseSectionController(CourseService courseService, CourseSectionService courseSectionService) {
    this.courseService = courseService;
    this.courseSectionService = courseSectionService;
  }

  @PostMapping("/instructor/add/section")
  public String addSection(Model model,
                           @ModelAttribute("section")CourseSection courseSection,
                           RedirectAttributes redirectAttributes){
      courseSection.setCreatedAt(new Date());
      courseSectionService.save(courseSection);
    redirectAttributes.addFlashAttribute("message", "Course Section Added Successfully");
    redirectAttributes.addFlashAttribute("alertType", "success");
    return "redirect:/instructor/add/lecture/" + courseSection.getCourse().getId();
  }

  @GetMapping("/instructor/delete/{courseId}/section/{id}")
  public String deleteSection(@PathVariable("id") Long id,@PathVariable("courseId") Long courseId, RedirectAttributes redirectAttributes){
    courseSectionService.deleteSection(id);
    redirectAttributes.addFlashAttribute("message", "Delete Course Section Successfully");
    redirectAttributes.addFlashAttribute("alertType", "success");
    return "redirect:/instructor/add/lecture/" + courseId;

  }
}
