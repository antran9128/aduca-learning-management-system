package com.aduca.lms.repository;

import com.aduca.lms.domain.Course;
import com.aduca.lms.domain.CourseGoal;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseGoalRepository extends JpaRepository<CourseGoal, Long> {
    List<CourseGoal> findByCourse_Id(Long id);
    @Transactional
    void deleteByCourse_Id(Long id);
}
