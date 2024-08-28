package com.aduca.lms.controller.backend;

import com.aduca.lms.domain.*;
import com.aduca.lms.domain.dto.CourseDTO;
import com.aduca.lms.service.CategoryService;
import com.aduca.lms.service.CourseGoalService;
import com.aduca.lms.service.CourseService;
import com.aduca.lms.service.SubCategoryService;
import com.aduca.lms.util.FileUploadUtil;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.*;

@Controller
public class CourseController {
  private CourseService courseService;
  private CategoryService categoryService;
  private SubCategoryService subCategoryService;
  private CourseGoalService courseGoalService;
  private final ServletContext servletContext;

  public CourseController(CourseGoalService courseGoalService,CourseService courseService, CategoryService categoryService, SubCategoryService subCategoryService, ServletContext servletContext) {
    this.courseService = courseService;
    this.categoryService = categoryService;
    this.subCategoryService = subCategoryService;
    this.servletContext = servletContext;
    this.courseGoalService = courseGoalService;
  }

  @GetMapping("/instructor/all/course")
  public String getAllCoursesPage(HttpServletRequest request, Model model){
    HttpSession session = request.getSession(false);
    Long id = (Long) session.getAttribute("id");

    model.addAttribute("courses", courseService.getAll(id));
    return "instructor/course/all_courses";
  }

  @GetMapping("/instructor/add/course")
  public String getAddCoursePage(Model model){
    model.addAttribute("course", new CourseDTO());
    model.addAttribute("categories", categoryService.getAll());
    return "instructor/course/add_courses";
  }

  @GetMapping("/subcategory/ajax/{id}")
  @ResponseBody
  public List<SubCategory> getSubCategories(@PathVariable("id") Long id){
      List<SubCategory> list = subCategoryService.findByCategory_Id(id);
      return list;
  }

  @PostMapping("/instructor/save/course")
  public String addCourse(@RequestParam("image") MultipartFile image,
                          @RequestParam("intro") MultipartFile video,
                          RedirectAttributes redirectAttributes,
                          @ModelAttribute("course") @Valid CourseDTO courseDTO,
                          BindingResult bindingResult,
                          Model model) throws IOException {
    // Validate video file
    if (video.isEmpty() || !video.getContentType().equals("video/mp4") || video.getSize() > 10000000) { // 10000000 bytes = 10MB
      redirectAttributes.addFlashAttribute("videoError", "Video is required and must be an MP4 file not exceeding 10MB.");
      return "redirect:/instructor/add/course";
    }

    if(image.isEmpty()){
      redirectAttributes.addFlashAttribute("imageError", "Thumbnail field is required.");
      return "redirect:/instructor/add/course";
    }

    if(bindingResult.hasErrors()){
      model.addAttribute("categories", categoryService.getAll());
      return "instructor/course/add_courses";
    }

    Course newCourse = courseService.courseDTOtoCourse(courseDTO);

    // upload image and video
    Course saveCourse = upload(image, video, newCourse);
    for (String goal: courseDTO.getCourseGoals()) {
        courseGoalService.save(new CourseGoal(saveCourse, goal, new Date()));
    }

    redirectAttributes.addFlashAttribute("message", "Add Course Successfully");
    redirectAttributes.addFlashAttribute("alertType", "success");
    return "redirect:/instructor/all/course";
  }




  private Course upload(MultipartFile image, MultipartFile video, Course course) throws IOException {
    // Handle course image upload
    String imageName = StringUtils.cleanPath(image.getOriginalFilename());
    course.setCourseImage(imageName);

    // Handle course image upload
    String videoName = StringUtils.cleanPath(video.getOriginalFilename());
    course.setVideo(videoName);

    Course savedCourse = courseService.save(course);

    String uploadDir1 = this.servletContext.getRealPath("/resources/upload/course/thumbnail/") + savedCourse.getId();
    FileUploadUtil.cleanDir(uploadDir1);
    FileUploadUtil.saveFile(uploadDir1, imageName, image);

    String uploadDir2 = this.servletContext.getRealPath("/resources/upload/course/video/") + savedCourse.getId();
    FileUploadUtil.cleanDir(uploadDir2);
    FileUploadUtil.saveFile(uploadDir2, videoName, video);
    return savedCourse;
  }

  @GetMapping("/instructor/course/update/{id}")
  public String getEditPage(@PathVariable("id") Long id, Model model){
    Course course = courseService.getById(id);
    List<SubCategory> subcategories = subCategoryService.findByCategory_Id(course.getCategory().getId());
    List<CourseGoal> courseGoals = courseGoalService.findByCourse_Id(id);
    List<String> cg = new ArrayList<>();
    for (CourseGoal goal: courseGoals) {
      cg.add(goal.getGoalName());
    }

    CourseDTO courseDTO = new CourseDTO();
    courseDTO.setCourseGoals(cg);
    model.addAttribute("course", course);
    model.addAttribute("categories", categoryService.getAll());
    model.addAttribute("subcategories", subcategories);
    model.addAttribute("courseDTO", courseDTO);
    model.addAttribute("counter", courseGoals.size());
    return "instructor/course/edit_course";
  }

  @PostMapping("/instructor/update/course")
  public String updateCourse(@ModelAttribute("course") Course course, RedirectAttributes redirectAttributes){
    Course courseDb = courseService.getById(course.getId());

    course.setUpdateAt(new Date());
    course.setCourseImage(course.getCourseImage());
    course.setVideo(courseDb.getVideo());
    course.setCreatedAt(courseDb.getCreatedAt());
    course.setCourseImage(courseDb.getCourseImage());
    course.setStatus(courseDb.isStatus());
    course.setCourseNameSlug(course.getCourseName());
    courseService.save(course);

    redirectAttributes.addFlashAttribute("message", "Edit Course Successfully");
    redirectAttributes.addFlashAttribute("alertType", "success");
    return "redirect:/instructor/course/update/" + course.getId();
  }

  @PostMapping("/instructor/update/course-image/{id}")
  public String updateCourseImage(@PathVariable("id") Long id, @RequestParam("image") MultipartFile image, RedirectAttributes redirectAttributes) throws IOException {
    Course course = courseService.getById(id);
    if(image.isEmpty()){
      redirectAttributes.addFlashAttribute("imageError", "Thumbnail field is required.");
      return "redirect:/instructor/course/update/" + course.getId();
    }
    String imageName = StringUtils.cleanPath(image.getOriginalFilename());
    course.setCourseImage(imageName);
    Course savedCourse = courseService.save(course);

    String uploadDir1 = this.servletContext.getRealPath("/resources/upload/course/thumbnail/") + savedCourse.getId();
    FileUploadUtil.cleanDir(uploadDir1);
    FileUploadUtil.saveFile(uploadDir1, imageName, image);
    redirectAttributes.addFlashAttribute("message", "Course Image Updated Successfully");
    redirectAttributes.addFlashAttribute("alertType", "success");
    return "redirect:/instructor/course/update/" + id;
  }

  @PostMapping("/instructor/update/course-video/{id}")
  public String updateCourseVideo(@PathVariable("id") Long id,@RequestParam("intro") MultipartFile video, RedirectAttributes redirectAttributes) throws IOException {
    Course course = courseService.getById(id);
    if (video.isEmpty() || !video.getContentType().equals("video/mp4") || video.getSize() > 10000000) { // 10000000 bytes = 10MB
      redirectAttributes.addFlashAttribute("videoError", "Video is required and must be an MP4 file not exceeding 10MB.");
      return "redirect:/instructor/course/update/" + id;
    }
    // Handle course image upload
    String videoName = StringUtils.cleanPath(video.getOriginalFilename());
    course.setVideo(videoName);
    Course savedCourse = courseService.save(course);
    String uploadDir2 = this.servletContext.getRealPath("/resources/upload/course/video/") + savedCourse.getId();
    FileUploadUtil.cleanDir(uploadDir2);
    FileUploadUtil.saveFile(uploadDir2, videoName, video);
    redirectAttributes.addFlashAttribute("message", "Course Video Updated Successfully");
    redirectAttributes.addFlashAttribute("alertType", "success");
    return "redirect:/instructor/course/update/" + id;
  }

  @PostMapping("/instructor/update/course-goal/{id}")
  public String updateCourseGoals(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,  @ModelAttribute("course") CourseDTO courseDTO){
    courseGoalService.deleteByCourse_Id(id);
    for (String goal: courseDTO.getCourseGoals()) {
      courseGoalService.save(new CourseGoal(new Course(id), goal, new Date()));
    }
    redirectAttributes.addFlashAttribute("message", "Course Goals Updated Successfully");
    redirectAttributes.addFlashAttribute("alertType", "success");
    return "redirect:/instructor/course/update/" + id;
  }

  @GetMapping("/instructor/course/delete/{id}")
  public String deleteCourse(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
    courseService.delete(id);
    redirectAttributes.addFlashAttribute("message", "Deleted Course Successfully");
    redirectAttributes.addFlashAttribute("alertType", "success");
    return "redirect:/instructor/all/course";
  }

  @GetMapping("/instructor/add/lecture/{id}")
  public String getAddLecturePage(@PathVariable("id") Long id, Model model){
    Course course = courseService.getById(id);
    model.addAttribute("course", course);
    model.addAttribute("section", new CourseSection());
    model.addAttribute("sections", course.getSections());

    return "instructor/course/add_lecture";
  }

  @GetMapping("/admin/all/course")
  public String AdminAllCourse(Model model){
    model.addAttribute("courses", courseService.findAllCourse());
    return "admin/course/all_course";
  }

  @PostMapping("/admin/course/updateStatus")
  @ResponseBody
  public Map<String, String> updateUserStatus(@RequestParam("course_id") Long courseId,
                                              @RequestParam("is_checked") int isChecked) {
    Map<String, String> response = new HashMap<>();
    try {
      Course course = courseService.getById(courseId);
      course.setStatus(isChecked == 1);
      courseService.save(course);
      response.put("message", "Course status updated successfully!");
    } catch (Exception e) {
      response.put("message", "Error occurred while updating user status.");
    }
    return response;
  }

  @GetMapping("/admin/course/details/{id}")
  public String getCourseDetails(Model model, @PathVariable("id") Long id){
    model.addAttribute("course", courseService.getById(id));
    return "admin/course/course_details";
  }
  }
