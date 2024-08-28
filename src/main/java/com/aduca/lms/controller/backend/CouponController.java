package com.aduca.lms.controller.backend;

import com.aduca.lms.domain.Coupon;
import com.aduca.lms.service.CouponService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
public class CouponController {
  private CouponService couponService;

  public CouponController(CouponService couponService) {
    this.couponService = couponService;
  }

  @GetMapping("/admin/all/coupon")
  public String getAllCoupons(Model model){
    model.addAttribute("coupons", couponService.getAllCoupons());
    model.addAttribute("now", new Date());
    return "admin/coupon/all_coupon";
  }

  @GetMapping("/admin/add/coupon")
  public String addCouponPage(Model model){
    model.addAttribute("coupon", new Coupon());
    return "admin/coupon/add_coupon";
  }

  @PostMapping("/admin/add/coupon")
  public String addCoupon(Coupon coupon, RedirectAttributes redirectAttributes){
    coupon.setCreatedAt(new Date());
    couponService.save(coupon);
    redirectAttributes.addFlashAttribute("message", "Add New Coupon Successfully");
    redirectAttributes.addFlashAttribute("alertType", "success");
    return "redirect:/admin/all/coupon";
  }

  @GetMapping("/admin/edit/coupon/{id}")
  public String editCouponPage(Model model, @PathVariable("id") Long id){
    model.addAttribute("coupon", couponService.findCouponById(id));
    return "admin/coupon/edit_coupon";
  }

  @PostMapping("/admin/edit/coupon")
  public String editCoupon(Coupon coupon, RedirectAttributes redirectAttributes){
    coupon.setUpdatedAt(new Date());
    couponService.save(coupon);
    redirectAttributes.addFlashAttribute("message", "Edit Coupon Successfully");
    redirectAttributes.addFlashAttribute("alertType", "success");
    return "redirect:/admin/all/coupon";
  }

  @GetMapping("/admin/delete/coupon/{id}")
  public String deleteCoupon(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
    couponService.deleteCoupon(id);
    redirectAttributes.addFlashAttribute("message", "Delete Coupon Successfully");
    redirectAttributes.addFlashAttribute("alertType", "success");
    return "redirect:/admin/all/coupon";
  }

}
