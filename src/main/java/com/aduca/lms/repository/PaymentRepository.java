package com.aduca.lms.repository;

import com.aduca.lms.domain.CartItem;
import com.aduca.lms.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
