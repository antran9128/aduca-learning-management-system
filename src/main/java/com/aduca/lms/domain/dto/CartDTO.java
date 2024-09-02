package com.aduca.lms.domain.dto;

import com.aduca.lms.domain.Course;

import java.util.List;

public class CartDTO {
  private List<CourseDTO> courseDTOS;
  private Double totalPrice;
  private Integer quantity;

  public CartDTO() {
  }


  public List<CourseDTO> getCourseDTOs() {
    return courseDTOS;
  }

  public void setCourseDTOs(List<CourseDTO> CourseDTOs) {
    this.courseDTOS = CourseDTOs;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
