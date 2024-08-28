package com.aduca.lms.controller;

import com.aduca.lms.domain.Category;
import com.aduca.lms.domain.Course;
import com.aduca.lms.domain.dto.CartDTO;
import com.aduca.lms.domain.dto.CourseDTO;
import com.aduca.lms.service.CartItemService;
import com.aduca.lms.service.CategoryService;
import com.aduca.lms.service.WishListService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {
  private WishListService wishListService;
  private CategoryService categoryService;
  private CartItemService cartItemService;

  public GlobalControllerAdvice(CategoryService categoryService,WishListService wishListService, CartItemService cartItemService) {
    this.categoryService = categoryService;
    this.wishListService = wishListService;
    this.cartItemService = cartItemService;
  }

  @ModelAttribute
  public void addCategoriesToModel(Model model, HttpServletRequest request) {
    List<Category> categories = categoryService.getAll();
    model.addAttribute("globalCategories", categories);
    HttpSession session = request.getSession(false);
    List<Course> courses = wishListService.getCoursesByUserId((Long) session.getAttribute("id"));
    model.addAttribute("wishCount", courses.size());
  }


}
