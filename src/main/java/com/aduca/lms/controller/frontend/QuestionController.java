package com.aduca.lms.controller.frontend;

import com.aduca.lms.domain.Question;
import com.aduca.lms.domain.User;
import com.aduca.lms.service.QuestionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
public class QuestionController {
  private QuestionService questionService;

  public QuestionController(QuestionService questionService, HttpSession session) {
    this.questionService = questionService;

  }

  @PostMapping("/user/question")
  public String userQuestion(HttpSession session, @ModelAttribute("question") Question question, RedirectAttributes redirectAttributes){
    question.setCreatedAt(new Date());
    question.setUser(new User((Long) session.getAttribute("id")));
    questionService.save(question);
    redirectAttributes.addFlashAttribute("message", "Message Send Successfully");
    redirectAttributes.addFlashAttribute("alertType", "success");
    return "redirect:/course/view/" + question.getCourse().getId();
  }

  @GetMapping("/instructor/all/question")
  public String instructorAllQuestion(Model model, HttpSession session){
    Long instructorId = (Long) session.getAttribute("id");
    List<Question> questions = questionService.getQuestionsByInstructorId(instructorId);
    for (Question question: questions) {
      question.setSentTime(questionService.getSentTimeRelativeToNow(question.getCreatedAt()));
    }
    model.addAttribute("questions", questions);

    return "instructor/question/all_question";
  }// End Method

  @GetMapping("/instructor/question/details/{id}")
  public String questionDetails(@PathVariable("id") Long id, Model model){
    Question question = questionService.getByQuestionId(id);
    question.setSentTime(questionService.getSentTimeRelativeToNow(question.getCreatedAt()));
    model.addAttribute("question", question);
    List<Question> replies = question.getReplies();
    for (Question rep: replies) {
      rep.setSentTime(questionService.getSentTimeRelativeToNow(rep.getCreatedAt()));
    }
    model.addAttribute("replies", replies);
    model.addAttribute("reply", new Question());
    return "instructor/question/question_details";
  }

  @PostMapping("/instructor/reply")
  public String instructorReplay(@ModelAttribute("reply") Question question, RedirectAttributes redirectAttributes, HttpSession session){
    question.setCreatedAt(new Date());
    questionService.save(question);
    redirectAttributes.addFlashAttribute("message", "Message Send Successfully");
    redirectAttributes.addFlashAttribute("alertType", "success");
    return "redirect:/instructor/question/details/" + question.getParent().getId();
  }

}
