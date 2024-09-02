package com.aduca.lms.controller.backend;

import com.aduca.lms.domain.CourseLecture;
import com.aduca.lms.domain.dto.CourseDTO;
import com.aduca.lms.domain.dto.CourseLectureDTO;
import com.aduca.lms.service.CourseLectureService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CourseLectureController {
  private CourseLectureService lectureService;

  public CourseLectureController(CourseLectureService lectureService) {
    this.lectureService = lectureService;
  }

  @PostMapping("/instructor/save-lecture")
  @ResponseBody
  public ResponseEntity<?> addNewLecture(@RequestBody CourseLectureDTO lectureDTO){
    CourseLecture lecture = lectureService.lectureDTOtoLecture(lectureDTO);
    lecture.setCreatedAt(new Date());
    lectureService.save(lecture);
    Map<String, String> response = new HashMap<>();
    response.put("success", "Lecture Saved Successfully");
    return ResponseEntity.ok(response);
  }

  @GetMapping("/instructor/edit/lecture/{id}")
  public String getEditLecturePage(@PathVariable("id") Long id, Model model){
    CourseLecture courseLecture = lectureService.findLectureById(id);
    model.addAttribute("lecture", courseLecture);
    return "instructor/course/edit_lecture";
  }

  @PostMapping("/instructor/edit/lecture")
  public String editLecture(RedirectAttributes redirectAttributes,
                            @ModelAttribute("lecture") CourseLecture lecture){
    CourseLecture lectureDb = lectureService.findLectureById(lecture.getId());
    lectureDb.setlectureTitle(lecture.getlectureTitle());
    lectureDb.setContent(lecture.getContent());
    lectureDb.setUrl(lecture.getUrl());
    lectureDb.setUpdatedAt(new Date());
    lectureService.save(lectureDb);

    redirectAttributes.addFlashAttribute("message", "Edit Course Lecture Successfully");
    redirectAttributes.addFlashAttribute("alertType", "success");
    return "redirect:/instructor/edit/lecture/" + lecture.getId();
  }

  @GetMapping("/instructor/delete/{courseId}/lecture/{id}")
  public String deleteLecture(@PathVariable("id") Long id,@PathVariable("courseId") Long courseId, RedirectAttributes redirectAttributes){
    lectureService.deleteLecture(id);
    redirectAttributes.addFlashAttribute("message", "Delete Course Lecture Successfully");
    redirectAttributes.addFlashAttribute("alertType", "success");
    return "redirect:/instructor/add/lecture/" + courseId;

  }
}
