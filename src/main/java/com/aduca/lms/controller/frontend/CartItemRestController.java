package com.aduca.lms.controller.frontend;

import com.aduca.lms.domain.CartItem;
import com.aduca.lms.domain.Coupon;
import com.aduca.lms.domain.Course;
import com.aduca.lms.domain.dto.CartDTO;
import com.aduca.lms.exception.UserNotFoundException;
import com.aduca.lms.repository.CouponRepository;
import com.aduca.lms.service.CartItemService;
import com.aduca.lms.service.CourseService;
import com.aduca.lms.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@SessionAttributes("coupon")
public class CartItemRestController {
  private CartItemService cartItemService;
  private UserService userService;
  private CourseService courseService;
  private CouponRepository couponRepository;
  public CartItemRestController(CartItemService cartItemService, UserService userService, CourseService courseService, CouponRepository couponRepository,  HttpSession session) {
    this.cartItemService = cartItemService;
    this.userService = userService;
    this.courseService = courseService;
    this.couponRepository = couponRepository;
  }

  @PostMapping("/cart/add/{id}")
  public HashMap<String, String> addToCart(@PathVariable("id") Long courseId, HttpServletRequest request) throws UserNotFoundException {
    HttpSession session = request.getSession(false);
    Long userId = (Long) session.getAttribute("id");
    HashMap<String, String> response = new HashMap<>();
    if(userId != null){
      CartItem exist = cartItemService.findByCourseIdAndUserId(courseId, userId);
      if(exist != null){
        response.put("error", "This course is already in your cart");
      }else{
        CartItem newCart = new CartItem();
        newCart.setCourse(new Course(courseId));

        newCart.setUser(userService.getUserById(userId));
        cartItemService.save(newCart);
        response.put("success", "Successfully added to your cart");
      }
    }else{
      response.put("error", "Please log in first");
    }
    return response;
  }

  @GetMapping("/minicart/remove/{id}")
  public Map<String, Object> deleteMinicart(@PathVariable("id") Long id, HttpServletRequest request){
    return getRemoveItem(id, request);
  }

  private Map<String, Object> getRemoveItem(@PathVariable("id") Long id, HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    Long userId = (Long) session.getAttribute("id");
    HashMap<String, Object> response = new HashMap<>();
    cartItemService.deleteByCourseIdAndUserId(id, (Long) session.getAttribute("id"));
    response.put("success", "Successfully Course Remove");
    return response;
  }

  @GetMapping("/mini/cart")
  public CartDTO getMiniCart(HttpServletRequest request){
    CartDTO cartDTO = cartItemService.getCartDTO(request);
    return cartDTO;
  }

  @GetMapping("/get-cart-course")
  public CartDTO getCart(HttpServletRequest request){
    CartDTO cartDTO = cartItemService.getCartDTO(request);
    return cartDTO;
  }

  @GetMapping("/cart-remove/{id}")
  public Map<String, Object> deleteCartItem(@PathVariable("id") Long id, HttpServletRequest request,HttpSession session){
    return getRemoveItem(id, request);
  }

  @PostMapping("/coupon-apply")
  public Map<String, Object> applyCoupon(@RequestParam("coupon_name") String couponName, HttpServletRequest request,  HttpSession session){
    Map<String, Object> response = new HashMap<>();
    Coupon coupon = couponRepository.findByCouponName(couponName);

    if (coupon == null || !coupon.isStatus()) {
      response.put("error", "Invalid coupon.");
      return response;
    }
    // Check if coupon is expired
    if (coupon.getCouponValidity().before(new Date())) {
      response.put("error", "This coupon has expired.");
      return response;
    }

    getUserCaryAndApplyCoupon(request, response, coupon,session);

    response.put("success", "Coupon Applied Successfully");
    response.put("validity",true);
    return response;
  }

  private void getUserCaryAndApplyCoupon(HttpServletRequest request, Map<String, Object> response, Coupon coupon,  HttpSession session) {
    // Retrieve user's cart and apply the discount
    CartDTO cart = cartItemService.getCartDTO(request);

    double discount = Double.parseDouble(coupon.getCouponDiscount());
    double totalPrice = cart.getTotalPrice();
    double discountedPrice = totalPrice - (totalPrice * discount / 100);

    session.setAttribute("coupon", Map.of(
      "couponName", coupon.getCouponName(),
      "couponDiscount", coupon.getCouponDiscount(),
      "discountAmount", (totalPrice * discount / 100),
      "totalAmount", discountedPrice
    ));
  }

  @GetMapping("/coupon-calculation")
  public Map<String, Object> CouponCalculation(HttpServletRequest request,HttpSession session){
    Map<String, Object> response = new HashMap<>();
    Map<String, Object> coupon = (Map<String, Object>) session.getAttribute("coupon");
    CartDTO cart = cartItemService.getCartDTO(request);
    Double cartTotal = cart.getTotalPrice();
    if (coupon != null) {
      Coupon coupon1 = couponRepository.findByCouponName((String) coupon.get("couponName"));
      getUserCaryAndApplyCoupon(request, response, coupon1, session);
      coupon = (Map<String, Object>) session.getAttribute("coupon");
      response.put("subtotal", cartTotal); // Implement this method to calculate the cart subtotal
      response.put("coupon_name", coupon.get("couponName"));
      response.put("coupon_discount", coupon.get("couponDiscount"));
      response.put("discount_amount", coupon.get("discountAmount"));
      response.put("total_amount", coupon.get("totalAmount"));
    } else {
      response.put("total", cartTotal); // Implement this method to calculate the cart total
    }
    return response;
  }

  @GetMapping("/coupon-remove")
  public Map<String, String> CouponRemove(SessionStatus sessionStatus){
    sessionStatus.setComplete();
    Map<String, String> response = new HashMap<>();
    response.put("success", "Coupon Removed Successfully");
    return response;
  }

  @PostMapping("/buy/course/{id}")
  public Map<String, String> buyCourse(@PathVariable("id") Long id, HttpSession session) throws UserNotFoundException {
    Map<String, String> response = new HashMap<>();
    Long userId = (Long) session.getAttribute("id");
    CartItem item = cartItemService.findByCourseIdAndUserId(id, userId);

    if(item != null){
      response.put("error", "Course is already in your cart");
      return response;
    }

    CartItem course = new CartItem();
    course.setUser(userService.getUserById(userId));
    course.setCourse(courseService.getById(id));

    cartItemService.save(course);

    response.put("success", "Successfully Added on Your Cart");
    return response;
  }


}
