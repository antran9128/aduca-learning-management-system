package com.aduca.lms.repository;

import com.aduca.lms.domain.CartItem;
import com.aduca.lms.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStatus(String status);

    List<Payment> findByOrderDate(String date);

  List<Payment> findByOrderMonthAndOrderYear(String month, String year);

  List<Payment> findByOrderYear(String year);
}
