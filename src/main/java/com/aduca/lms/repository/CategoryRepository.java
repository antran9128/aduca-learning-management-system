package com.aduca.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aduca.lms.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
