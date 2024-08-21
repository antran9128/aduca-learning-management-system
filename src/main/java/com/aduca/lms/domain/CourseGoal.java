package com.aduca.lms.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "course_goals")
public class CourseGoal extends IdBasedEntity{

  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;
  private String goalName;
  private Date createdAt;
  private Date updateAt;

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public String getGoalName() {
    return goalName;
  }

  public void setGoalName(String goalName) {
    this.goalName = goalName;
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
