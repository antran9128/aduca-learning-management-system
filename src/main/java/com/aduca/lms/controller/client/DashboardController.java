package com.aduca.lms.controller.client;

import java.io.IOException;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aduca.lms.domain.ResetPassword;
import com.aduca.lms.domain.User;
import com.aduca.lms.exception.UserNotFoundException;
import com.aduca.lms.service.UserService;
import com.aduca.lms.util.FileUploadUtil;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class DashboardController {

    private UserService userService;
    private final ServletContext servletContext;

    public DashboardController(UserService userService, ServletContext servletContext) {
        this.userService = userService;
        this.servletContext = servletContext;
    }

    @GetMapping("/dashboard")
    public String getUserDashboard(HttpServletRequest request, Model model) {
        return "client/dashboard/show";
    }

    @GetMapping("/profile")
    public String getUserProfile(HttpServletRequest request, Model model) throws UserNotFoundException {
        HttpSession session = request.getSession(false);
        Long id = (Long) session.getAttribute("id");
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "client/dashboard/profile";
    }

    @PostMapping("/update-profile")
    public String updateProfile(Model model,
            @ModelAttribute @Valid User user,
            BindingResult bindingResult,
            @RequestParam("avatar") MultipartFile multipartFile,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request) throws IOException, UserNotFoundException {

        if (bindingResult.hasErrors()) {
            // User currentUser = this.userService.getUserById(user.getId());
            // model.addAttribute("user", currentUser);
            return "client/dashboard/profile";
        }
        try {
            User currentUser = this.userService.getUserById(user.getId());
            currentUser.setName(user.getName());
            currentUser.setUsername(user.getUsername());
            currentUser.setEmail(user.getEmail());
            currentUser.setAddress(user.getAddress());
            currentUser.setPhone(user.getPhone());
            currentUser.setUpdatedAt(new Date());

            if (!multipartFile.isEmpty()) {
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                currentUser.setPhoto(fileName);

                String uploadDir = this.servletContext.getRealPath("/resources/client/images/avatars/")
                        + currentUser.getId();

                FileUploadUtil.cleanDir(uploadDir);
                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

            }

            userService.updateSessionInfo(request, currentUser);

            userService.saveUser(currentUser);
            redirectAttributes.addFlashAttribute("message", "   User Profile Updated Successfully");
            redirectAttributes.addFlashAttribute("alertType", "success");
            return "redirect:/profile";
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", "Something is wrong, please try again later!");
            redirectAttributes.addFlashAttribute("alertType", "error");
            return "redirect:/profile";
        }
    }

    @GetMapping("/change-password")
    public String getChangePassword(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long id = (Long) session.getAttribute("id");
        ResetPassword password = new ResetPassword();
        password.setId(id);
        model.addAttribute("password", password);
        return "client/dashboard/change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(Model model,
            @ModelAttribute("password") @Valid ResetPassword pass,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) throws UserNotFoundException {
        if (bindingResult.hasErrors()) {

            return "client/dashboard/change-password";
        }

        User user = userService.getUserById(pass.getId());

        if (!userService.checkIfValidOldPassword(user, pass.getOldPassword())) {
            redirectAttributes.addFlashAttribute("message", "Old password does not match");
            redirectAttributes.addFlashAttribute("alertType", "error");
        } else if (!pass.getNewPassword().equals(pass.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("errorConfirm", "The new password confirmation field does not match!");

        } else {
            userService.changePassword(user, pass.getNewPassword());
            redirectAttributes.addFlashAttribute("message", "Admin Password Updated Successfully");
            redirectAttributes.addFlashAttribute("alertType", "success");
        }

        return "redirect:/change-password";
    }

}
