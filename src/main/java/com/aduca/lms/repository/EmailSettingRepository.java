package com.aduca.lms.repository;

import com.aduca.lms.domain.CourseSection;
import com.aduca.lms.domain.EmailSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailSettingRepository extends JpaRepository<EmailSetting, Long> {
  EmailSetting findTopByOrderByIdAsc();
}
