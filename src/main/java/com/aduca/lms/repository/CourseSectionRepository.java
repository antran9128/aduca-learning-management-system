package com.aduca.lms.repository;

import com.aduca.lms.domain.CourseSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseSectionRepository extends JpaRepository<CourseSection, Long> {

}
