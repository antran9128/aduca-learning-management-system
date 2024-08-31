package com.aduca.lms.service;

import com.aduca.lms.domain.Payment;
import com.aduca.lms.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
  private PaymentRepository repository;

  public PaymentService(PaymentRepository repository) {
    this.repository = repository;
  }

  public Payment save(Payment payment){
    return repository.save(payment);
  }

    public List<Payment> findByStatus(String status) {
      return repository.findByStatus(status);
    }

  public Payment findById(Long id) {
    return repository.findById(id).get();
  }
}
