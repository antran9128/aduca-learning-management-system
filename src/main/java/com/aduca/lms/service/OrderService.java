package com.aduca.lms.service;

import com.aduca.lms.domain.Order;
import com.aduca.lms.repository.OrderRepository;
import org.springframework.stereotype.Service;

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
}
