package com.aduca.lms.controller.backend;

import com.aduca.lms.domain.Payment;
import com.aduca.lms.service.OrderService;
import com.aduca.lms.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class OrderController {
  private OrderService orderService;
  private PaymentService paymentService;

  public OrderController(OrderService orderService, PaymentService paymentService) {
    this.orderService = orderService;
    this.paymentService = paymentService;
  }


  @GetMapping("/admin/pending/order")
  public String adminPendingOrder(Model model){
   List<Payment> payments =  paymentService.findByStatus("pending");
   model.addAttribute("payments", payments);
   return "admin/order/pending_orders";
  } // End Method

  @GetMapping("/admin/order/details/{id}")
  public String orderDetails(@PathVariable("id") Long id, Model model){
    Payment payment = paymentService.findById(id);
    model.addAttribute("payment", payment);
    model.addAttribute("orders", payment.getOrders());
    return "admin/order/order_details";
  }

  @GetMapping("/admin/confirm/order")
  public String adminConfirmOrder(Model model){
    List<Payment> payments =  paymentService.findByStatus("confirm");
    model.addAttribute("payments", payments);
    return "admin/order/confirm_orders";
  } // End Method

  @GetMapping("/pending/confirm/{id}")
  public String pendingToConfirm(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
    Payment payment = paymentService.findById(id);
    payment.setStatus("confirm");
    paymentService.save(payment);
    redirectAttributes.addFlashAttribute("message", "Order Confrim Successfully");
    redirectAttributes.addFlashAttribute("alertType", "success");
    return "redirect:/admin/confirm/order";
  }
}
