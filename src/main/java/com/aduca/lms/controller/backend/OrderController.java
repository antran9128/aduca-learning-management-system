package com.aduca.lms.controller.backend;

import com.aduca.lms.domain.*;
import com.aduca.lms.service.CourseSectionService;
import com.aduca.lms.service.OrderService;
import com.aduca.lms.service.PaymentService;
import com.aduca.lms.service.QuestionService;
import com.aduca.lms.util.ExportPDFUtil;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class OrderController {
  private OrderService orderService;
  private PaymentService paymentService;
  private CourseSectionService courseSectionService;
  private QuestionService questionService;
  public OrderController(QuestionService questionService,OrderService orderService, PaymentService paymentService, CourseSectionService courseSectionService) {
    this.orderService = orderService;
    this.paymentService = paymentService;
    this.courseSectionService = courseSectionService;
    this.questionService = questionService;
  }


  @GetMapping("/admin/pending/order")
  public String adminPendingOrder(Model model){
   List<Payment> payments =  paymentService.findByStatus("pending");
   model.addAttribute("payments", payments);
   return "admin/order/pending_orders";
  } // End Method

  @GetMapping("/admin/order/details/{id}")
  public String orderDetails(@PathVariable("id") Long id, Model model){
    Payment payment = paymentService.findById(id);
    model.addAttribute("payment", payment);
    model.addAttribute("orders", payment.getOrders());
    return "admin/order/order_details";
  }

  @GetMapping("/admin/confirm/order")
  public String adminConfirmOrder(Model model){
    List<Payment> payments =  paymentService.findByStatus("confirm");
    model.addAttribute("payments", payments);
    return "admin/order/confirm_orders";
  } // End Method

  @GetMapping("/pending/confirm/{id}")
  public String pendingToConfirm(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
    Payment payment = paymentService.findById(id);
    payment.setStatus("confirm");
    paymentService.save(payment);
    redirectAttributes.addFlashAttribute("message", "Order Confrim Successfully");
    redirectAttributes.addFlashAttribute("alertType", "success");
    return "redirect:/admin/confirm/order";
  }

  @GetMapping("/instructor/all/order")
  public String instructorAllOrder(HttpSession session, Model model){
    Long instructorId = (Long) session.getAttribute("id");
    List<Order> orders = orderService.findOrderByInstructorId(instructorId);
    model.addAttribute("orders", orders);
    return "instructor/order/all_orders";
  }

  @GetMapping("/instructor/order/details/{id}")
  public String instructorOrderDetails(@PathVariable("id") Long id, HttpSession session, Model model){
    Payment payment = paymentService.findById(id);
    Long instructorId = (Long) session.getAttribute("id");
    List<Order> orders = orderService.findOrderByInstructorIdAndPaymentId(instructorId, id);
    model.addAttribute("payment", payment);
    model.addAttribute("orders", payment.getOrders());
    return "instructor/order/order_details";
  }

  @GetMapping("/instructor/order/invoice/{id}")
  public void exportInvoice(@PathVariable("id") Long paymentId, HttpSession session, HttpServletResponse response) throws IOException, DocumentException {
    Payment payment = paymentService.findById(paymentId);
    Long instructorId = (Long) session.getAttribute("id");
    List<Order> orders = orderService.findOrderByInstructorIdAndPaymentId(instructorId, paymentId);
    ExportPDFUtil.generateInvoicePDF(payment, orders, response);
  }

  @GetMapping("/mycourse")
  public String myCourse(HttpSession session, Model model){
    Long userId = (Long) session.getAttribute("id");
    List<Course> courses = orderService.findCoursesByUserId(userId);
    model.addAttribute("courses", courses);
    return "client/dashboard/my_all_course";
  }

  @GetMapping("/course/view/{course_id}")
  public String courseView(@PathVariable("course_id") Long courseId, HttpSession session, Model model){
    Long userId = (Long) session.getAttribute("id");
    Course course = orderService.findOrderByUserIdAndCourseId(userId, courseId).getCourse();
    List<CourseSection> courseSections = courseSectionService.getSectionsByCourseId(courseId);
    List<Question> allQuestions = questionService.getQuestionByCourseId(courseId);
    List<Question> questions = questionService.getParentQuestionByCourseId(courseId);
    for (Question question: questions) {
      question.setSentTime(questionService.getSentTimeRelativeToNow(question.getCreatedAt()));
      for (Question rep: question.getReplies()) {
        rep.setSentTime(questionService.getSentTimeRelativeToNow(rep.getCreatedAt()));
      }
    }
    model.addAttribute("questions", questions);
    model.addAttribute("allQuestions", allQuestions);
    model.addAttribute("course", course);
    model.addAttribute("courseSections", courseSections);
    model.addAttribute("question", new Question());
    return "client/mycourse/course_view";
  }
}
