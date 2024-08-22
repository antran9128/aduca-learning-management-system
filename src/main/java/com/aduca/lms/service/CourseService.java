package com.aduca.lms.service;

import com.aduca.lms.domain.Course;
import com.aduca.lms.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
  private CourseRepository repo;

  public CourseService(CourseRepository repo) {
    this.repo = repo;
  }

  public List<Course> getAll(Long id){
    return repo.findByInstructor_Id(id);
  }

  public Course save(Course course){
    return repo.save(course);
  }

  public Course getById(Long id){
    return repo.findById(id).get();
  }

  public void delete(Long id){
    repo.deleteById(id);
  }
}
