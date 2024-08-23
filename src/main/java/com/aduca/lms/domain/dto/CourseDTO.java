package com.aduca.lms.domain.dto;

import com.aduca.lms.domain.Category;
import com.aduca.lms.domain.CourseGoal;
import com.aduca.lms.domain.SubCategory;
import com.aduca.lms.domain.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO {

  private Long categoryId;
  private Long subCategoryId;
  private Long instructorId;
  private List<String> courseGoals = new ArrayList<>();

  private String courseImage;
  private String courseTitle;
  private String courseName;
  private String courseNameSlug;
  private String description;
  private String video;
  private String label;
  private String duration;
  private String resources;
  private String certificate;
  private double sellingPrice;
  private double discountPrice;
  private String prerequisites;
  private String bestseller;
  private String featured;
  private String highestrated;

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public Long getSubCategoryId() {
    return subCategoryId;
  }

  public void setSubCategoryId(Long subCategoryId) {
    this.subCategoryId = subCategoryId;
  }

  public Long getInstructorId() {
    return instructorId;
  }

  public void setInstructorId(Long instructorId) {
    this.instructorId = instructorId;
  }

  public List<String> getCourseGoals() {
    return courseGoals;
  }

  public void setCourseGoals(List<String> courseGoals) {
    this.courseGoals = courseGoals;
  }

  public String getCourseImage() {
    return courseImage;
  }

  public void setCourseImage(String courseImage) {
    this.courseImage = courseImage;
  }

  public String getCourseTitle() {
    return courseTitle;
  }

  public void setCourseTitle(String courseTitle) {
    this.courseTitle = courseTitle;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public String getCourseNameSlug() {
    return courseNameSlug;
  }

  public void setCourseNameSlug(String courseNameSlug) {
    this.courseNameSlug = courseNameSlug;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getVideo() {
    return video;
  }

  public void setVideo(String video) {
    this.video = video;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public String getResources() {
    return resources;
  }

  public void setResources(String resources) {
    this.resources = resources;
  }

  public String getCertificate() {
    return certificate;
  }

  public void setCertificate(String certificate) {
    this.certificate = certificate;
  }

  public double getSellingPrice() {
    return sellingPrice;
  }

  public void setSellingPrice(double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  public double getDiscountPrice() {
    return discountPrice;
  }

  public void setDiscountPrice(double discountPrice) {
    this.discountPrice = discountPrice;
  }

  public String getPrerequisites() {
    return prerequisites;
  }

  public void setPrerequisites(String prerequisites) {
    this.prerequisites = prerequisites;
  }

  public String getBestseller() {
    return bestseller;
  }

  public void setBestseller(String bestseller) {
    this.bestseller = bestseller;
  }

  public String getFeatured() {
    return featured;
  }

  public void setFeatured(String featured) {
    this.featured = featured;
  }

  public String getHighestrated() {
    return highestrated;
  }

  public void setHighestrated(String highestrated) {
    this.highestrated = highestrated;
  }
}
