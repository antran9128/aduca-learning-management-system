package com.aduca.lms.domain.dto;

public class CourseLectureDTO {
  private Long courseId;
  private Long sectionId;
  private String lectureTitle;
  private String lectureUrl;
  private String content;
  private String video;

  public String getVideo() {
    return video;
  }

  public void setVideo(String video) {
    this.video = video;
  }

  public Long getCourseId() {
    return courseId;
  }

  public void setCourseId(Long courseId) {
    this.courseId = courseId;
  }

  public Long getSectionId() {
    return sectionId;
  }

  public void setSectionId(Long sectionId) {
    this.sectionId = sectionId;
  }

  public String getLectureTitle() {
    return lectureTitle;
  }

  public void setLectureTitle(String lectureTitle) {
    this.lectureTitle = lectureTitle;
  }

  public String getLectureUrl() {
    return lectureUrl;
  }

  public void setLectureUrl(String lectureUrl) {
    this.lectureUrl = lectureUrl;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
