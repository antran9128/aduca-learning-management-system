package com.aduca.lms.controller.admin;

import java.io.IOException;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aduca.lms.domain.Role;
import com.aduca.lms.domain.User;
import com.aduca.lms.exception.UserNotFoundException;
import com.aduca.lms.service.UserService;
import com.aduca.lms.util.FileUploadUtil;

import jakarta.servlet.ServletContext;

@Controller
public class AdminController {

    private UserService userService;
    private final ServletContext servletContext;

    public AdminController(UserService userService, ServletContext servletContext) {
        this.userService = userService;
        this.servletContext = servletContext;
    }

    @GetMapping("/admin/user/create")
    public String createNewUser() {
//        User user = new User();
//        user.setName("An Tran");
//        user.setUsername("Admin001");
//        user.setEmail("hehe@gmail.com");
//        user.setPassword("123");
//        user.setPhoto("avatar-3.png");
//        user.setAddress("Thai Binh");
//        user.setRole(new Role(1L));
//        userService.saveUser(user);
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
    public String updateAdminProfile(Model model, @ModelAttribute("admin") User admin,
            @RequestParam("avatarFile") MultipartFile multipartFile,
            RedirectAttributes redirectAttributes) throws IOException {

        try {
            User currentUser = this.userService.getUserById(admin.getId());
            currentUser.setName(admin.getName());
            currentUser.setUsername(admin.getUsername());
            currentUser.setEmail(admin.getEmail());
            currentUser.setAddress(admin.getAddress());
            currentUser.setPhone(admin.getPhone());
            currentUser.setUpdatedAt(new Date());

            if (!multipartFile.isEmpty()) {
                String fileName =StringUtils.cleanPath(multipartFile.getOriginalFilename());
                currentUser.setPhoto(fileName);

                String uploadDir = this.servletContext.getRealPath("/resources/admin/images/avatars/") + currentUser.getId();

                FileUploadUtil.cleanDir(uploadDir);
                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

            }

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
}
