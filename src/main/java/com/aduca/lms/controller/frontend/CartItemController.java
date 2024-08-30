package com.aduca.lms.controller.frontend;

import com.aduca.lms.domain.Order;
import com.aduca.lms.domain.Payment;
import com.aduca.lms.domain.dto.CartDTO;
import com.aduca.lms.exception.UserNotFoundException;
import com.aduca.lms.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class CartItemController {
  private CartItemService cartItemService;
  private OrderService orderService;
  private PaymentService paymentService;
  private CourseService courseService;
  private UserService userService;
  @Autowired
  private EmailService emailService;
  public CartItemController(CartItemService cartItemService,
                            OrderService orderService,
                            PaymentService paymentService,
                            CourseService courseService,
                            UserService userService) {
    this.cartItemService = cartItemService;
    this.orderService = orderService;
    this.paymentService = paymentService;
    this.courseService = courseService;
    this.userService = userService;
  }

  @GetMapping("/mycart")
  public String getMyCart(){
    return "client/cart/view_mycart";
  }

  @GetMapping("/checkout")
  public String checkoutCreate(HttpServletRequest request, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
    Long userId = (Long) session.getAttribute("id");
    if (userId != null) {  // Check if user is authenticated
      CartDTO cart = cartItemService.getCartDTO(request);
      double cartTotal = cart.getTotalPrice();
      int cartQty = cart.getQuantity();

      if (cartTotal > 0) {
        model.addAttribute("carts", cart.getCourseDTOs());
        model.addAttribute("cartTotal", cartTotal);
        model.addAttribute("cartQty", cartQty);
        return "client/checkout/checkout_view";  // Return the checkout view
      } else {
        redirectAttributes.addFlashAttribute("message", "Add at least one course");
        redirectAttributes.addFlashAttribute("alertType", "error");
        return "redirect:/homepage";  // Redirect to homepage
      }
    } else {
      redirectAttributes.addFlashAttribute("message", "You need to log in first");
      redirectAttributes.addFlashAttribute("alertType", "error");
      return "redirect:/login";  // Redirect to login page
    }
  }

  @PostMapping("/payment")
  public String payment(HttpServletRequest request, RedirectAttributes redirectAttributes) throws UserNotFoundException {
    HttpSession session = request.getSession(false);
    Map<String, Object> coupon = (Map<String, Object>) session.getAttribute("coupon");
    CartDTO cart = cartItemService.getCartDTO(request);

    String totalAmount;
    if (coupon != null) {
      totalAmount = coupon.get("totalAmount") + "";
    }else {
      totalAmount = cart.getTotalPrice() + "";
    }

    // create a new Payment Record

    Payment payment = new Payment();
    payment.setName(request.getParameter("name"));
    payment.setEmail(request.getParameter("email"));
    payment.setPhone(request.getParameter("phone"));
    payment.setAddress(request.getParameter("address"));
    payment.setCashDelivery(request.getParameter("cash_delivery"));
    payment.setTotalAmount(totalAmount);
    payment.setPaymentType("Direct Payment");
    Random random = new Random();
    int randomNum = 10000000 + random.nextInt(90000000);
    payment.setInvoiceNo("EOS" + randomNum);
    payment.setStatus("pending");
    payment.setOrderDate(getCurrentDate("dd MMMM yyyy"));
    payment.setOrderYear(getCurrentDate("yyyy"));
    payment.setOrderMonth(getCurrentDate("MMMM"));

    payment = paymentService.save(payment);
    String[] courseTitles = request.getParameterValues("course_title[]");
    String[] courseIds = request.getParameterValues("course_id[]");
    String[] prices = request.getParameterValues("price[]");
    String[] intructorIds = request.getParameterValues("instructor_id[]");

    for (int i = 0; i < courseTitles.length; i++) {
      Long courseId = Long.valueOf(courseIds[i]);
      String courseTitle = courseTitles[i];
      Long instructorId = Long.valueOf(intructorIds[i]);
      Double price = Double.valueOf(prices[i]);
      Long userId = (Long) session.getAttribute("id");
      Order existingOrder = orderService.findOrderByUserIdAndCourseId(userId, courseId);

      if (existingOrder != null) {
        redirectAttributes.addFlashAttribute("message", "You have already enrolled in this course");
        redirectAttributes.addFlashAttribute("alertType", "error");
        return "redirect:/checkout"; // Adjust the redirect URL
      }

      Order order = new Order();
      order.setPayment(payment);
      order.setUser(userService.getUserById(userId));
      order.setCourse(courseService.getById(courseId));
      order.setInstructor(userService.getUserById(instructorId));
      order.setCourseTitle(courseTitle);
      order.setPrice(price);
      order.setCreatedAt(new Date());
      orderService.save(order);
      // Send email notification
      emailService.sendOrderConfirmationEmail((String) session.getAttribute("email"), courseTitle);
    }

    cartItemService.deleteByUserId((Long)session.getAttribute("id"));

    if ("stripe".equals(payment.getCashDelivery())) {
      System.out.println("stripe");
    } else {
      redirectAttributes.addFlashAttribute("message", "Cash Payment Submit Successfully");
      redirectAttributes.addFlashAttribute("alertType", "success");

      return "redirect:/homepage";
    }

    return "redirect:/homepage";
  }

  private String getCurrentDate(String pattern) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    return LocalDateTime.now().format(formatter);
  }
}
