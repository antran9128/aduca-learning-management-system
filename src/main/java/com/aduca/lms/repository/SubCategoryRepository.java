package com.aduca.lms.repository;

import com.aduca.lms.domain.Category;
import com.aduca.lms.domain.Course;
import com.aduca.lms.domain.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
  List<SubCategory> findByCategory_Id(Long id);
}
