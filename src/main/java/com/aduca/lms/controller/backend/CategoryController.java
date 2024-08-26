package com.aduca.lms.controller.backend;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;
import com.aduca.lms.domain.Category;
import com.aduca.lms.service.CategoryService;
import com.aduca.lms.util.FileUploadUtil;

import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;

@Controller
public class CategoryController {

    private CategoryService categoryService;
    private final ServletContext servletContext;

    public CategoryController(CategoryService categoryService, ServletContext servletContext) {
        this.categoryService = categoryService;
        this.servletContext = servletContext;
    }

    @GetMapping("/admin/category")
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "admin/category/all_category";
    }

    @GetMapping("/admin/category/create")
    public String getCreateCategories(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category/add_category";
    }

    @PostMapping("/admin/category/create")
    public String createCategories(Model model, @ModelAttribute("category") Category category,
            @RequestParam("photo") MultipartFile multipartFile,
            RedirectAttributes redirectAttributes) throws IOException {

        String slug = category.getCategoryName().toLowerCase().strip().replace(" ", "-");
        category.setCategorySlug(slug);
        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            category.setImage(fileName);
            Category savedCategory = categoryService.save(category);
            String uploadDir = this.servletContext.getRealPath("/resources/upload/category/") + savedCategory.getId();

            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } else {
            categoryService.save(category);
        }

        redirectAttributes.addFlashAttribute("message", "Add New Category Successfully");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return "redirect:/admin/category";
    }

    @GetMapping("/admin/category/edit/{id}")
    public String getEditPage(Model model, @PathVariable("id") Long id) {
        Category cat = categoryService.getCategoryById(id);
        model.addAttribute("category", cat);
        return "admin/category/edit_category";
    }

    @PostMapping("/admin/category/edit")
    public String postEditPage(Model model,
            @ModelAttribute("category") @Valid Category category,
            @RequestParam("photo") MultipartFile multipartFile,
            RedirectAttributes redirectAttributes) throws IOException {
        String slug = category.getCategoryName().toLowerCase().strip().replace(" ", "-");
        category.setCategorySlug(slug);
        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            category.setImage(fileName);
            Category savedCategory = categoryService.save(category);
            String uploadDir = this.servletContext.getRealPath("/resources/upload/category/") + savedCategory.getId();

            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } else {
            categoryService.save(category);
        }
        redirectAttributes.addFlashAttribute("message", "Edit Category Successfully");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return "redirect:/admin/category";
    }

    @GetMapping("/admin/category/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        categoryService.delete(id);
        String categoryDir = "/resources/cat-photos/" + id;
        FileUploadUtil.removeDir(categoryDir);
        redirectAttributes.addFlashAttribute("message", "Delete Category Successfully");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return "redirect:/admin/category";
    }

}
