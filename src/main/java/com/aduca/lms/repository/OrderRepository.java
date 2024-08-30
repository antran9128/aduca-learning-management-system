package com.aduca.lms.repository;

import com.aduca.lms.domain.Order;
import com.aduca.lms.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  Order findOrderByUser_IdAndCourse_Id(Long userId, Long courseId);
}
