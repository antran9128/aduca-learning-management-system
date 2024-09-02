package com.aduca.lms.repository;

import com.aduca.lms.domain.Course;
import com.aduca.lms.domain.Order;
import com.aduca.lms.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  Order findOrderByUser_IdAndCourse_Id(Long userId, Long courseId);

    List<Order> findOrderByInstructor_Id(Long instructorId);

  List<Order> findOrderByInstructor_IdAndPayment_Id(Long instructorId, Long paymentId);

  @Query("SELECT o FROM Order o WHERE o.id IN (" +
    "SELECT MAX(sub.id) FROM Order sub " +
    "WHERE sub.instructor.id = :instructorId " +
    "GROUP BY sub.payment.id) " +
    "ORDER BY o.id DESC")
  List<Order> findLatestOrdersByInstructorId(@Param("instructorId") Long instructorId);

  @Query("SELECT o.course FROM Order o WHERE o.user.id = :userId ORDER BY o.id DESC")
  List<Course> findCoursesByUserIdOrderedByOrderIdDesc(@Param("userId") Long userId);
}
