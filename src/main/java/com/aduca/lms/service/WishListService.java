package com.aduca.lms.service;

import com.aduca.lms.domain.Course;
import com.aduca.lms.domain.WishList;
import com.aduca.lms.exception.WishListNotFoundException;
import com.aduca.lms.repository.WishListRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WishListService {
  private WishListRepository repo;

  public WishListService(WishListRepository repo) {
    this.repo = repo;
  }

  public WishList save(WishList w){
    return repo.save(w);
  }

  public WishList findById(Long id) throws WishListNotFoundException {
    try {
      return repo.findById(id).get();
    }catch(NoSuchElementException e){
      throw new WishListNotFoundException("No wishlist id : " + id + "found!");
    }
  }

  public WishList findByCourse_IdAndUser_Id(Long course_id, Long user_id) throws WishListNotFoundException {
    try {
      return repo.findByCourse_IdAndUser_Id(course_id, user_id);
    }catch(NoSuchElementException e){
      throw new WishListNotFoundException("No wishlist found!");
    }
  }

  public List<Course> getCoursesByUserId(Long id){
    return repo.findCoursesByUserId(id);
  }

  @Transactional
  public void deleteByCourseId(Long courseId, Long userId) {
    repo.deleteByCourseIdAndUserId(courseId, userId);
  }
}
