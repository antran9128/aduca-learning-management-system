package com.aduca.lms.repository;

import com.aduca.lms.domain.CartItem;
import com.aduca.lms.domain.Category;
import com.aduca.lms.domain.Course;
import com.aduca.lms.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
  CartItem findByCourse_IdAndUser_Id(Long course_id, Long user_id);

  @Query("SELECT w.course FROM CartItem w WHERE w.user.id = :userId")
  List<Course> findCoursesByUserId(Long userId);

  @Modifying
  @Query("DELETE FROM CartItem w WHERE w.course.id = :courseId AND w.user.id = :userId")
  void deleteByCourseIdAndUserId(Long courseId, Long userId);

  CartItem findByUser_Id(Long userId);

  @Modifying
  void deleteByUser_Id(Long id);
}
