package com.aduca.lms.service;

import com.aduca.lms.domain.Question;
import com.aduca.lms.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class QuestionService {
  private QuestionRepository repo;

  public QuestionService(QuestionRepository repo) {
    this.repo = repo;
  }

  public Question save(Question question) {
    return repo.save(question);
  }

  public List<Question> getQuestionsByInstructorId(Long instructorId) {
    return repo.findByInstructor_IdAndParent_IdIsNullOrderByIdDesc(instructorId);
  }

  public String getSentTimeRelativeToNow(Date createdAt) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(createdAt);

    Calendar now = Calendar.getInstance();

    long diffInMillis = now.getTimeInMillis() - calendar.getTimeInMillis();

    long seconds = diffInMillis / 1000 % 60;
    long minutes = diffInMillis / (1000 * 60) % 60;
    long hours = diffInMillis / (1000 * 60 * 60) % 24;
    long days = diffInMillis / (1000 * 60 * 60 * 24) % 7;
    long weeks = diffInMillis / (1000 * 60 * 60 * 24 * 7) % 4;
    long months = diffInMillis / (1000 * 60 * 60 * 24 * 30) % 12;
    long years = diffInMillis / (1000 * 60 * 60 * 24 * 365);

    StringBuilder timeDiff = new StringBuilder();
    if (years > 0) {
      timeDiff.append(years).append(" year").append(years > 1 ? "s" : "").append(" ");
    }
    else if (months > 0) {
      timeDiff.append(months).append(" month").append(months > 1 ? "s" : "").append(" ");
    }
    else if (weeks > 0) {
      timeDiff.append(weeks).append(" week").append(weeks > 1 ? "s" : "").append(" ");
    }
    else if (days > 0) {
      timeDiff.append(days).append(" day").append(days > 1 ? "s" : "").append(" ");
    }
    else if (hours > 0) {
      timeDiff.append(hours).append(" hour").append(hours > 1 ? "s" : "").append(" ");
    }
    else if (minutes > 0) {
      timeDiff.append(minutes).append(" minute").append(minutes > 1 ? "s" : "").append(" ");
    }
    else if (seconds > 0) {
      timeDiff.append(seconds).append(" second").append(seconds > 1 ? "s" : "").append(" ");
    }

    if (timeDiff.length() == 0) {
      return "Just now";
    } else {
      return timeDiff.append("ago").toString().trim();
    }
  }

  public Question getByQuestionId(Long id) {
    return repo.findById(id).get();
  }

  public List<Question> getAll() {
    return repo.findAll();
  }

  public List<Question> getQuestionByCourseId(Long courseId) {
    return repo.findByCourse_Id(courseId);
  }

  public List<Question> getParentQuestionByCourseId(Long courseId) {
    return repo.findByCourse_IdAndParent_IdIsNullOrderByIdAsc(courseId);
  }
}
