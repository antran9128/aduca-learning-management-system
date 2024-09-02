package com.aduca.lms.service;

import com.aduca.lms.domain.CourseSection;
import com.aduca.lms.repository.CourseRepository;
import com.aduca.lms.repository.CourseSectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseSectionService {
  private CourseSectionRepository repo;

  public CourseSectionService(CourseSectionRepository repo) {
    this.repo = repo;
  }

  public CourseSection save(CourseSection courseSection){
    return repo.save(courseSection);
  }

  public void deleteSection(Long id) {
    repo.deleteById(id);
  }

    public List<CourseSection> getSectionsByCourseId(Long courseId) {
      return repo.findByCourseIdOrderByIdAsc(courseId);
    }
}
