package com.aduca.lms.service;

import com.aduca.lms.domain.Payment;
import com.aduca.lms.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
  private PaymentRepository repository;

  public PaymentService(PaymentRepository repository) {
    this.repository = repository;
  }

  public Payment save(Payment payment){
    return repository.save(payment);
  }
}
