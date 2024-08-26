package com.aduca.lms.service;

import com.aduca.lms.domain.CourseGoal;
import com.aduca.lms.repository.CourseGoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseGoalService {
  private CourseGoalRepository repo;

  public CourseGoalService(CourseGoalRepository repo) {
    this.repo = repo;
  }

  public CourseGoal save(CourseGoal cg){
    return repo.save(cg);
  }

    public List<CourseGoal> findByCourse_Id(Long id) {
      return repo.findByCourse_Id(id);
    }

    public void deleteByCourse_Id(Long id) {
      repo.deleteByCourse_Id(id);
    }
}
