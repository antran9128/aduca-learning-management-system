package com.aduca.lms.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "course_sections")
public class CourseSection extends IdBasedEntity{
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "courseSection", cascade = CascadeType.ALL)
    private List<CourseLecture> lectures = new ArrayList<>();

    private String sectionTitle;
    private Date createdAt;
    private Date updateAt;

  public CourseSection() {
  }

  public CourseSection(Course course, String sectionTitle, Date createdAt, Date updateAt) {
    this.course = course;
    this.sectionTitle = sectionTitle;
    this.createdAt = createdAt;
    this.updateAt = updateAt;
  }

  public CourseSection(Long id) {
    this.id = id;
  }

  public List<CourseLecture> getLectures() {
    return lectures;
  }

  public void setLectures(List<CourseLecture> lectures) {
    this.lectures = lectures;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public String getsectionTitle() {
    return sectionTitle;
  }

  public void setsectionTitle(String sectionTitle) {
    this.sectionTitle = sectionTitle;
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
}
