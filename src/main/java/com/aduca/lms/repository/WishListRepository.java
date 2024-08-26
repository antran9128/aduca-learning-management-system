package com.aduca.lms.repository;

import com.aduca.lms.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
  WishList findByCourse_IdAndUser_Id(Long course_id, Long user_id);
}
