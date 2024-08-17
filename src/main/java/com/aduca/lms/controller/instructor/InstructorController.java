package com.aduca.lms.controller.instructor;

import java.io.IOException;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class InstructorController {
    private UserService userService;
    private final ServletContext servletContext;

    public InstructorController(UserService userService, ServletContext servletContext) {
        this.userService = userService;
        this.servletContext = servletContext;
    }

    @GetMapping("/instructor")
    public String getDashboard(Model model) {
        return "instructor/dashboard/show";
    }

    @GetMapping("/instructor/user/create")
    public String createNewUser() {
        User user = new User();
        user.setName("An Tran");
        user.setUsername("Instructor");
        user.setEmail("instructor@gmail.com");
        user.setPassword("12345");

        user.setAddress("Thai Binh");
        user.setRole(new Role(2L));
        userService.saveUser(user);
        return "instructor/dashboard/show";
    }

    @GetMapping("/instructor/profile/{id}")
    public String getinstructorProfile(@PathVariable("id") Long id, Model model) {
        try {
            User instructor = userService.getUserById(id);
            model.addAttribute("instructor", instructor);
            return "instructor/dashboard/profile";

        } catch (UserNotFoundException e) {
            return "instructor/dashboard/show";
        }
    }

    @PostMapping("/instructor/profile/update")
    public String updateinstructorProfile(Model model,
            @ModelAttribute("instructor") User instructor,
            @RequestParam("avatarFile") MultipartFile multipartFile,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request) throws IOException {

        try {
            User currentUser = this.userService.getUserById(instructor.getId());
            currentUser.setName(instructor.getName());
            currentUser.setUsername(instructor.getUsername());
            currentUser.setEmail(instructor.getEmail());
            currentUser.setAddress(instructor.getAddress());
            currentUser.setPhone(instructor.getPhone());
            currentUser.setUpdatedAt(new Date());

            if (!multipartFile.isEmpty()) {
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                currentUser.setPhoto(fileName);

                String uploadDir = this.servletContext.getRealPath("/resources/instructor/images/avatars/")
                        + currentUser.getId();

                FileUploadUtil.cleanDir(uploadDir);
                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

            }

            userService.updateSessionInfo(request, currentUser);

            userService.saveUser(currentUser);
            redirectAttributes.addFlashAttribute("message", "instructor Profile Updated Successfully");
            redirectAttributes.addFlashAttribute("alertType", "success");
            return "redirect:/instructor/profile/" + instructor.getId();
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", "Something is wrong, please try again later!");
            redirectAttributes.addFlashAttribute("alertType", "error");
            return "redirect:/instructor/profile/" + instructor.getId();
        }

    }

    @GetMapping("/instructor/change-password/{id}")
    public String getChangePassword(Model model, @PathVariable("id") Long id) {
        try {
            User instructor = userService.getUserById(id);
            model.addAttribute("instructor", instructor);
            ResetPassword password = new ResetPassword();
            password.setId(id);
            model.addAttribute("password", password);
            return "instructor/auth/change-password";
        } catch (UserNotFoundException e) {
            return "instructor/dashboard/show";
        }
    }

    @PostMapping("/instructor/change-password")
    public String changePassword(Model model,
            @ModelAttribute("password") @Valid ResetPassword pass,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) throws UserNotFoundException {
        if (bindingResult.hasErrors()) {
            User instructor = userService.getUserById(pass.getId());
            model.addAttribute("instructor", instructor);
            return "instructor/auth/change-password";
        }

        User user = userService.getUserById(pass.getId());

        if (!userService.checkIfValidOldPassword(user, pass.getOldPassword())) {
            redirectAttributes.addFlashAttribute("message", "Old password does not match");
            redirectAttributes.addFlashAttribute("alertType", "error");
        } else if (!pass.getNewPassword().equals(pass.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("errorConfirm", "The new password confirmation field does not match!");

        } else {
            userService.changePassword(user, pass.getNewPassword());
            redirectAttributes.addFlashAttribute("message", "instructor Password Updated Successfully");
            redirectAttributes.addFlashAttribute("alertType", "success");
        }

        return "redirect:/instructor/change-password/" + user.getId();
    }
}
