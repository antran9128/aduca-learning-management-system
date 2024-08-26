package com.aduca.lms.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "courses")
@Entity
public class Course extends IdBasedEntity{
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne
  @JoinColumn(name = "subcategory_id")
  private SubCategory subCategory;

  @ManyToOne
  @JoinColumn(name = "instructor_id")
  private User instructor;

  @OneToMany(mappedBy = "course", cascade=CascadeType.ALL)
  private List<CourseGoal> courseGoals = new ArrayList<>();

  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
  private List<CourseLecture> lectures = new ArrayList<>();

  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
  private List<CourseSection> sections = new ArrayList<>();

  @Transient
  private Double discountPercent;

  private String courseImage;
  private String courseTitle;
  private String courseName;
  private String courseNameSlug;
  @Column(length=1000)
  private String description;
  private String video;
  private String label;
  private String duration;
  private String resources;
  private String certificate;
  private Double sellingPrice;
  private Double discountPrice;
  private String prerequisites;
  private String bestseller;
  private String featured;
  private String highestrated;
  private boolean status;
  private Date createdAt;
  private Date updateAt;

  public Course(Long id) {
    this.id = id;
  }
  public Course() {
  }

  public List<CourseLecture> getLectures() {
    return lectures;
  }

  public void setLectures(List<CourseLecture> lectures) {
    this.lectures = lectures;
  }

  public List<CourseSection> getSections() {
    return sections;
  }

  public void setSections(List<CourseSection> sections) {
    this.sections = sections;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public SubCategory getSubCategory() {
    return subCategory;
  }

  public void setSubCategory(SubCategory subCategory) {
    this.subCategory = subCategory;
  }

  public User getInstructor() {
    return instructor;
  }

  public void setInstructor(User instructor) {
    this.instructor = instructor;
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
    this.courseNameSlug = courseNameSlug.toLowerCase().strip().replace(" ", "-");
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

  public Double getSellingPrice() {
    return sellingPrice;
  }

  public void setSellingPrice(Double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  public Double getDiscountPrice() {
    return discountPrice;
  }

  public void setDiscountPrice(Double discountPrice) {
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

  public Date getUpdateAt() {
    return updateAt;
  }

  public void setUpdateAt(Date updateAt) {
    this.updateAt = updateAt;
  }

  public List<CourseGoal> getCourseGoals() {
    return courseGoals;
  }

  public void setCourseGoals(List<CourseGoal> courseGoals) {
    this.courseGoals = courseGoals;
  }

  public Double getDiscountPercent() {
    if(this.discountPrice == null){
      return Double.valueOf(0);
    }

    Double amount = sellingPrice - discountPrice;
    Double result = amount / sellingPrice * 100.0;
    return Math.round(result * 100.0) / 100.0;
  }

  public void setDiscountPercent(Double discountPercent) {
    this.discountPercent = discountPercent;
  }
}
