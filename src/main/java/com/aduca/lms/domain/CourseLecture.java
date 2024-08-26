package com.aduca.lms.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "course_lectures")
public class CourseLecture extends IdBasedEntity{
  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;

  @ManyToOne
  @JoinColumn(name = "section_id")
  private CourseSection courseSection;

  private String lectureTitle;
  private String video;
  private String url;
  private String content;
  private Date createdAt;
  private Date updatedAt;

  public CourseLecture() {
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public CourseSection getCourseSection() {
    return courseSection;
  }

  public void setCourseSection(CourseSection courseSection) {
    this.courseSection = courseSection;
  }

  public String getlectureTitle() {
    return lectureTitle;
  }

  public void setlectureTitle(String lectureTitle) {
    this.lectureTitle = lectureTitle;
  }

  public String getVideo() {
    return video;
  }

  public void setVideo(String video) {
    this.video = video;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
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


}
