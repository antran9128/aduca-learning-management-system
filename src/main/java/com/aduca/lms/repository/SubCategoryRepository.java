package com.aduca.lms.repository;

import com.aduca.lms.domain.Category;
import com.aduca.lms.domain.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

}
