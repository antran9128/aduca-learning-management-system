package com.aduca.lms.service;

import com.aduca.lms.domain.Category;
import com.aduca.lms.domain.Course;
import com.aduca.lms.domain.SubCategory;
import com.aduca.lms.domain.User;
import com.aduca.lms.domain.dto.CourseDTO;
import com.aduca.lms.repository.CourseRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseService {
  private CourseRepository repo;

  public CourseService(CourseRepository repo) {
    this.repo = repo;
  }

  public List<Course> getAll(Long id){
    return repo.findByInstructor_Id(id);
  }

  public Course save(Course course){
    return repo.save(course);
  }

  public Course getById(Long id){
    return repo.findById(id).get();
  }

  public void delete(Long id){
    repo.deleteById(id);
  }

  public Course courseDTOtoCourse(CourseDTO courseDTO){
    Course newCourse = new Course();
    newCourse.setCategory(new Category(courseDTO.getCategoryId()));
    newCourse.setSubCategory(new SubCategory(courseDTO.getSubCategoryId()));
    newCourse.setInstructor(new User(courseDTO.getInstructorId()));
    newCourse.setCourseTitle(courseDTO.getCourseTitle());
    newCourse.setCourseName(courseDTO.getCourseName());
    newCourse.setCourseNameSlug(courseDTO.getCourseName());
    newCourse.setDescription(courseDTO.getDescription());
    newCourse.setLabel(courseDTO.getLabel());
    newCourse.setDuration(courseDTO.getDuration());
    newCourse.setResources(courseDTO.getResources());
    newCourse.setCertificate(courseDTO.getCertificate());
    newCourse.setSellingPrice(courseDTO.getSellingPrice());
    newCourse.setDiscountPrice(courseDTO.getDiscountPrice());
    newCourse.setPrerequisites(courseDTO.getPrerequisites());
    newCourse.setBestseller(courseDTO.getBestseller());
    newCourse.setHighestrated(courseDTO.getHighestrated());
    newCourse.setFeatured(courseDTO.getFeatured());
    newCourse.setStatus(true);
    newCourse.setCreatedAt(new Date());
    return newCourse;
  }

  public List<Course> getCoursesLimit(int limit) {
    Pageable pageable = PageRequest.of(0, limit);
    return repo.findByStatus(true, pageable);
  }

  public List<Course> findAllCourse(){
    return repo.findAll();
  }

  public List<Course> getCoursesByCategoryAndStatus(Category category){
    return repo.findByCategoryAndStatus(category, true);
  }

  public List<Course> getCoursesBySubCategoryAndStatus(SubCategory subcategory) {
    return repo.findBySubCategoryAndStatus(subcategory, true);
  }
}
