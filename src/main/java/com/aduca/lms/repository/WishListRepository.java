package com.aduca.lms.repository;

import com.aduca.lms.domain.Course;
import com.aduca.lms.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
  WishList findByCourse_IdAndUser_Id(Long course_id, Long user_id);

  @Query("SELECT w.course FROM WishList w WHERE w.user.id = :userId")
  List<Course> findCoursesByUserId(Long userId);

  @Modifying
  @Query("DELETE FROM WishList w WHERE w.course.id = :courseId AND w.user.id = :userId")
  void deleteByCourseIdAndUserId(Long courseId, Long userId);
}
