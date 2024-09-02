package com.aduca.lms.domain;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question extends IdBasedEntity{
  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "instructor_id")
  private User instructor;

  @ManyToOne
  @JoinColumn(name = "parent_id")
  private Question parent;

  @OneToMany(mappedBy = "parent")
  @OrderBy("id asc")
  private List<Question> replies = new ArrayList<>();

  private String subject;
  private String quest;
  private Date createdAt;
  private Date updatedAt;
  @Transient
  private String sentTime;
  public Question() {
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public User getInstructor() {
    return instructor;
  }

  public void setInstructor(User instructor) {
    this.instructor = instructor;
  }

  public Question getParent() {
    return parent;
  }

  public void setParent(Question parent) {
    this.parent = parent;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getQuest() {
    return quest;
  }

  public void setQuest(String quest) {
    this.quest = quest;
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

  public String getSentTime() {
    return sentTime;
  }

  public void setSentTime(String sentTime) {
    this.sentTime = sentTime;
  }

  public List<Question> getReplies() {
    return replies;
  }

  public void setReplies(List<Question> replies) {
    this.replies = replies;
  }
}
