package com.aduca.lms.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "categories")
public class Category extends IdBasedEntity{

    @Size(min = 2, message = "Category name must have a minimum of 2 characters")
    private String categoryName;

    private String categorySlug;
    private String image;
    private Date createdAt;
    private Date updatedAt;

    @OneToMany(mappedBy = "category", cascade= CascadeType.ALL)
    private List<SubCategory> subCategories = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<Course> courses = new ArrayList<>();

    public Category() {
    }

    public Category(String categoryName,
            String categorySlug, String image, Date createdAt, Date updatedAt) {
        this.categoryName = categoryName;
        this.categorySlug = categorySlug;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Category(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategorySlug() {
        return categorySlug;
    }

    public void setCategorySlug(String categorySlug) {
        this.categorySlug = categorySlug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public List<SubCategory> getSubCategories() {
      return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
      this.subCategories = subCategories;
    }

    public List<Course> getCourses() {
      return courses;
    }

    public void setCourses(List<Course> courses) {
      this.courses = courses;
    }
}
