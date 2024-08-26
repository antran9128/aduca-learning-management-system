package com.aduca.lms.controller;

import com.aduca.lms.domain.Category;
import com.aduca.lms.service.CategoryService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {

  private CategoryService categoryService;

  public GlobalControllerAdvice(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @ModelAttribute
  public void addCategoriesToModel(Model model) {
    List<Category> categories = categoryService.getAll();
    model.addAttribute("globalCategories", categories);
  }
}
