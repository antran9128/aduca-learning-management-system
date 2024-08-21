package com.aduca.lms.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "sub_categories")
public class SubCategory extends IdBasedEntity {
    @Size(min = 2, message = "Sub category name must have a minimum of 2 characters")
    private String subcategoryName;
    private String subcategorySlug;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private Date createdAt;
    private Date updatedAt;

  public SubCategory() {
  }

  public SubCategory(String subcategoryName, String subcategorySlug, Category category, Date createdAt, Date updatedAt) {
    this.subcategoryName = subcategoryName;
    this.subcategorySlug = subcategorySlug;
    this.category = category;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getSubcategoryName() {
    return subcategoryName;
  }

  public void setSubcategoryName(String subcategoryName) {
    this.subcategoryName = subcategoryName;
  }

  public String getSubcategorySlug() {
    return subcategorySlug;
  }

  public void setSubcategorySlug(String subcategorySlug) {
    this.subcategorySlug = subcategorySlug;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
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
