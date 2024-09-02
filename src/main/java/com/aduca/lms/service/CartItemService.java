package com.aduca.lms.service;

import com.aduca.lms.domain.CartItem;
import com.aduca.lms.domain.Coupon;
import com.aduca.lms.domain.Course;
import com.aduca.lms.domain.dto.CartDTO;
import com.aduca.lms.domain.dto.CourseDTO;
import com.aduca.lms.repository.CartItemRepository;
import com.aduca.lms.repository.CouponRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartItemService {
  private CartItemRepository repo;
  private CourseService courseService;
  public CartItemService(CartItemRepository repo, CourseService courseService) {
    this.repo = repo;
    this.courseService = courseService;
  }


  public CartItem findByCourseIdAndUserId(Long courseId, Long userId) {
    return repo.findByCourse_IdAndUser_Id(courseId, userId);
  }

  public CartItem save(CartItem newCart) {
    return repo.save(newCart);
  }

  public Double totalPrice(List<Course> courseList){
    Double total = 0.0;
    for (Course course: courseList) {
      total += course.getPrice();
    }

    return total;
  }

  public List<Course> findCoursesByUserId(Long userId){
    return repo.findCoursesByUserId(userId);
  }

  @Transactional
  public void deleteByCourseIdAndUserId(Long courseId, Long userId) {
    repo.deleteByCourseIdAndUserId(courseId, userId);
  }

  public CartDTO getCartDTO(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    Long userId = (Long) session.getAttribute("id");
    CartDTO cartDTO = new CartDTO();
    List<Course> courses = findCoursesByUserId(userId);
    List<CourseDTO> courseDTOS = courseService.coursesToCourseDTOs(courses);
    cartDTO.setCourseDTOs(courseDTOS);
    cartDTO.setQuantity(courses.size());
    cartDTO.setTotalPrice(totalPrice(courses));
    return cartDTO;
  }
  @Transactional
  public void deleteByUserId(Long id) {
    repo.deleteByUser_Id(id);
  }
}
