package com.aduca.lms.controller.backend;

import com.aduca.lms.domain.Payment;
import com.aduca.lms.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.http.HttpRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ReportController {
  @Autowired
  private PaymentService paymentService;

  @GetMapping("/admin/report/view")
  public String reportView(){
    return "admin/report/report_view";
  }

  @PostMapping("/admin/search/by/date")
  public String searchByDate(HttpServletRequest request, Model model) {
    try {
      String dateStr = request.getParameter("date");
      if (dateStr != null && !dateStr.isEmpty()) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy");

        Date date = inputFormat.parse(dateStr);

        String formattedDate = outputFormat.format(date);

        List<Payment> payments = paymentService.findByOrderDate(formattedDate);

        model.addAttribute("payments", payments);
        model.addAttribute("formatDate", formattedDate);
      } else {
        model.addAttribute("message", "Date parameter is missing.");
        model.addAttribute("alertType", "error");
        return "admin/report/report_view";
      }
      return "admin/report/report_by_date";
    } catch (ParseException e) {
      model.addAttribute("message", "Date parameter is missing.");
      model.addAttribute("alertType", "error");
      return "admin/report/report_view";
    }
  }

  @PostMapping("/admin/search/by/month")
  public String searchByMonth(HttpServletRequest request, Model model) {
    String month = request.getParameter("month");
    String year = request.getParameter("year_name");

    if(month == null || year == null){
      model.addAttribute("message", "Date parameter is missing.");
      model.addAttribute("alertType", "error");
      return "admin/report/report_view";
    }
    List<Payment> payments = paymentService.findByOrderMonth(month, year);
    model.addAttribute("payments", payments);
    model.addAttribute("month", month);
    model.addAttribute("year", year);
    return "admin/report/report_by_month";
  }

  @PostMapping("/admin/search/by/year")
  public String searchByYear(HttpServletRequest request, Model model) {
    String year = request.getParameter("year");

    if(year == null){
      model.addAttribute("message", "Date parameter is missing.");
      model.addAttribute("alertType", "error");
      return "admin/report/report_view";
    }
    List<Payment> payments = paymentService.findByOrderYear(year);
    model.addAttribute("payments", payments);
    model.addAttribute("year", year);
    return "admin/report/report_by_year";
  }
}
