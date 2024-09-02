package com.aduca.lms.service;

import com.aduca.lms.domain.Course;
import com.aduca.lms.domain.Order;
import com.aduca.lms.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
  private OrderRepository repository;

  public OrderService(OrderRepository repository) {
    this.repository = repository;
  }

  public Order findOrderByUserIdAndCourseId(Long userId, Long courseId){
    return repository.findOrderByUser_IdAndCourse_Id(userId, courseId);
  }

  public Order save(Order order) {
    return repository.save(order);
  }

  public List<Order> findOrderByInstructorId(Long instructorId) {
  return repository.findLatestOrdersByInstructorId(instructorId);
  }

  public List<Order> findOrderByInstructorIdAndPaymentId(Long instructorId, Long paymentId) {
    return repository.findOrderByInstructor_IdAndPayment_Id(instructorId, paymentId);
  }

  public List<Course> findCoursesByUserId(Long userId){
    return repository.findCoursesByUserIdOrderedByOrderIdDesc(userId);
  }
}
