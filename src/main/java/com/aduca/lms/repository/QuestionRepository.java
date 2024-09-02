package com.aduca.lms.repository;

import com.aduca.lms.domain.Payment;
import com.aduca.lms.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

  List<Question> findByInstructor_IdAndParent_IdIsNullOrderByIdDesc(Long instructorId);

  List<Question> findByCourse_Id(Long courseId);

  List<Question> findByCourse_IdAndParent_IdIsNullOrderByIdAsc(Long courseId);
}
