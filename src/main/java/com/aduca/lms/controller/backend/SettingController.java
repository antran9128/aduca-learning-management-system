package com.aduca.lms.controller.backend;

import com.aduca.lms.domain.EmailSetting;
import com.aduca.lms.domain.SubCategory;
import com.aduca.lms.repository.EmailSettingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SettingController {

  private EmailSettingRepository emailSettingRepository;

  public SettingController(EmailSettingRepository emailSettingRepository) {
    this.emailSettingRepository = emailSettingRepository;
  }

  @GetMapping("/admin/smpt/setting")
  public String SmtpSetting(Model model){
    EmailSetting smpt = emailSettingRepository.findTopByOrderByIdAsc();
    model.addAttribute("smpt", smpt);
    return "admin/setting/email_setting";
  }

  @PostMapping("/admin/smtp/update")
  public String SmtpSettingUpdate(@ModelAttribute("smpt") EmailSetting smpt, RedirectAttributes redirectAttributes){
    emailSettingRepository.save(smpt);
    redirectAttributes.addFlashAttribute("message", "Smtp Setting Updated Successfully");
    redirectAttributes.addFlashAttribute("alertType", "success");
    return "redirect:/admin/smpt/setting";
  }
}
