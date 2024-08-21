package com.aduca.lms.controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aduca.lms.domain.ResetPassword;
import com.aduca.lms.domain.Role;
import com.aduca.lms.domain.User;
import com.aduca.lms.exception.UserNotFoundException;
import com.aduca.lms.service.UserService;
import com.aduca.lms.util.FileUploadUtil;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class AdminController {

    private UserService userService;
    private final ServletContext servletContext;

    public AdminController(UserService userService, ServletContext servletContext) {
        this.userService = userService;
        this.servletContext = servletContext;
    }

    @GetMapping("/admin")
    public String getDashboard(Model model) {
        return "admin/dashboard/show";
    }

    @GetMapping("/admin/user/create")
    public String createNewUser() {
        User user = new User();
        user.setName("An Tran");
        user.setUsername("Admin001");
        user.setEmail("admin@gmail.com");
        user.setPassword("12345");
        user.setPhoto("avatar-3.png");
        user.setAddress("Thai Binh");
        user.setRole(new Role(1L));
        userService.saveUser(user);
        return "admin/dashboard/show";
    }

    @GetMapping("/admin/profile/{id}")
    public String getAdminProfile(@PathVariable("id") Long id, Model model) {
        try {
            User admin = userService.getUserById(id);
            model.addAttribute("admin", admin);
            return "admin/dashboard/profile";

        } catch (UserNotFoundException e) {
            return "admin/dashboard/show";
        }
    }

    @PostMapping("/admin/profile/update")
    public String updateAdminProfile(Model model,
            @ModelAttribute("admin") User admin,
            @RequestParam("avatarFile") MultipartFile multipartFile,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request) throws IOException {

        try {
            User currentUser = this.userService.getUserById(admin.getId());
            currentUser.setName(admin.getName());
            currentUser.setUsername(admin.getUsername());
            currentUser.setEmail(admin.getEmail());
            currentUser.setAddress(admin.getAddress());
            currentUser.setPhone(admin.getPhone());
            currentUser.setUpdatedAt(new Date());

            if (!multipartFile.isEmpty()) {
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                currentUser.setPhoto(fileName);

                String uploadDir = this.servletContext.getRealPath("/resources/admin/images/avatars/")
                        + currentUser.getId();

                FileUploadUtil.cleanDir(uploadDir);
                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

            }

            userService.updateSessionInfo(request, currentUser);

            userService.saveUser(currentUser);
            redirectAttributes.addFlashAttribute("message", "Admin Profile Updated Successfully");
            redirectAttributes.addFlashAttribute("alertType", "success");
            return "redirect:/admin/profile/" + admin.getId();
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", "Something is wrong, please try again later!");
            redirectAttributes.addFlashAttribute("alertType", "error");
            return "redirect:/admin/profile/" + admin.getId();
        }

    }

    @GetMapping("/admin/change-password/{id}")
    public String getChangePassword(Model model, @PathVariable("id") Long id) {
        try {
            User admin = userService.getUserById(id);
            model.addAttribute("admin", admin);
            ResetPassword password = new ResetPassword();
            password.setId(id);
            model.addAttribute("password", password);
            return "admin/auth/change-password";
        } catch (UserNotFoundException e) {
            return "admin/dashboard/show";
        }
    }

    @PostMapping("/admin/change-password")
    public String changePassword(Model model,
            @ModelAttribute("password") @Valid ResetPassword pass,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) throws UserNotFoundException {
        if (bindingResult.hasErrors()) {
            User admin = userService.getUserById(pass.getId());
            model.addAttribute("admin", admin);
            return "admin/auth/change-password";
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

        return "redirect:/admin/change-password/" + user.getId();
    }

    @PostMapping("/admin/user/updateStatus")
    @ResponseBody
    public Map<String, String> updateUserStatus(@RequestParam("user_id") Long userId,
                                                @RequestParam("is_checked") int isChecked) {
      Map<String, String> response = new HashMap<>();
      try {
        User user = userService.getUserById(userId);
        user.setStatus(isChecked == 1);
        userService.saveUser(user);
        response.put("message", "User status updated successfully!");
      } catch (Exception e) {
        response.put("message", "Error occurred while updating user status.");
      }
      return response;
    }
}
