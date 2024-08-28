package com.aduca.lms.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "coupons")
public class Coupon extends IdBasedEntity{
  private String couponName;
  private String couponDiscount;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date couponValidity;
  private boolean status = true;
  private Date createdAt;
  private Date updatedAt;

  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;

  @ManyToOne
  @JoinColumn(name = "instructor_id")
  private User instructor;

  public Coupon() {
  }

  public String getCouponName() {
    return couponName;
  }

  public void setCouponName(String couponName) {
    this.couponName = couponName;
  }

  public String getCouponDiscount() {
    return couponDiscount;
  }

  public void setCouponDiscount(String couponDiscount) {
    this.couponDiscount = couponDiscount;
  }


  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public User getInstructor() {
    return instructor;
  }

  public void setInstructor(User instructor) {
    this.instructor = instructor;
  }

  public Date getCouponValidity() {
    return couponValidity;
  }

  public void setCouponValidity(Date couponValidity) {
    this.couponValidity = couponValidity;
  }
}
