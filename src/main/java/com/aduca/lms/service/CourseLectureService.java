package com.aduca.lms.service;

import com.aduca.lms.domain.Course;
import com.aduca.lms.domain.CourseLecture;
import com.aduca.lms.domain.CourseSection;
import com.aduca.lms.domain.dto.CourseLectureDTO;
import com.aduca.lms.repository.CourseLectureRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseLectureService {
  private CourseLectureRepository repo;

  public CourseLectureService(CourseLectureRepository repo) {
    this.repo = repo;
  }

  public CourseLecture save(CourseLecture lecture){
    return repo.save(lecture);
  }

  public CourseLecture lectureDTOtoLecture(CourseLectureDTO lectureDTO){
    CourseLecture courseLecture = new CourseLecture();
    courseLecture.setCourse(new Course(lectureDTO.getCourseId()));
    courseLecture.setCourseSection(new CourseSection(lectureDTO.getSectionId()));
    courseLecture.setlectureTitle(lectureDTO.getLectureTitle());
    courseLecture.setUrl(lectureDTO.getLectureUrl());
    courseLecture.setVideo(lectureDTO.getVideo());
    courseLecture.setContent(lectureDTO.getContent());

    return courseLecture;
  }

  public CourseLecture findLectureById(Long id) {
    return repo.findById(id).get();
  }

  public void deleteLecture(Long id) {
    repo.deleteById(id);
  }
}
