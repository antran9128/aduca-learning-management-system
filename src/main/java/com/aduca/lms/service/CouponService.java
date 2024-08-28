package com.aduca.lms.service;

import com.aduca.lms.domain.Coupon;
import com.aduca.lms.repository.CouponRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService {
  private CouponRepository repo;

  public CouponService(CouponRepository repo) {
    this.repo = repo;
  }

  public Coupon save(Coupon coupon){
    return repo.save(coupon);
  }

  public List<Coupon> getAllCoupons(){
    return repo.findAll();
  }


  public Coupon findCouponById(Long id) {
    return repo.findById(id).get();
  }

  public void deleteCoupon(Long id) {
    repo.deleteById(id);
  }
}
