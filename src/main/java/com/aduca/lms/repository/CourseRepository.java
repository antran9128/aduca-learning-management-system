package com.aduca.lms.repository;

import com.aduca.lms.domain.Category;
import com.aduca.lms.domain.Course;
import com.aduca.lms.domain.SubCategory;
import com.aduca.lms.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
  List<Course> findByInstructor_Id(Long instructorId);
  List<Course> findByStatus(boolean status, Pageable pageable);
  List<Course> findByCategoryAndStatus(Category category, boolean b);

  List<Course> findBySubCategoryAndStatus(SubCategory subcategory,boolean b);

  List<Course> findByInstructorAndStatus(User user, boolean b);
}
