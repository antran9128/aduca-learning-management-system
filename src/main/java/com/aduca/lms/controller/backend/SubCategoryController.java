package com.aduca.lms.controller.backend;

import com.aduca.lms.domain.SubCategory;
import com.aduca.lms.service.CategoryService;
import com.aduca.lms.service.SubCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SubCategoryController {
  private SubCategoryService subCategoryService;
  private CategoryService categoryService;

  public SubCategoryController(SubCategoryService categoryService, CategoryService CategoryService) {
    this.subCategoryService = categoryService;
    this.categoryService = CategoryService;
  }

  @GetMapping("/admin/sub-category")
  public String getAllSubCats(Model model){
    List<SubCategory> list = subCategoryService.getAll();
    model.addAttribute("subCategories", list);
    return "admin/sub_category/all_sub";
  }

  @GetMapping("/admin/sub-category/create")
  public String getCreateNew(Model model){
    model.addAttribute("sub", new SubCategory());
    model.addAttribute("categories", categoryService.getAll());
    return "admin/sub_category/add_sub";
  }

  @PostMapping("/admin/sub-category/save")
  public String getCreateNew(Model model, @ModelAttribute("sub") SubCategory sub, RedirectAttributes redirectAttributes){
    sub.setSubcategorySlug(sub.getSubcategoryName().toLowerCase().strip().replace(" ", "-"));
    subCategoryService.save(sub);
    redirectAttributes.addFlashAttribute("message", "Add New SubCategory Successfully");
    redirectAttributes.addFlashAttribute("alertType", "success");
    return "redirect:/admin/sub-category";
  }

  @GetMapping("/admin/sub-category/edit/{id}")
  public String getEditPage(Model model, @PathVariable("id") Long id){
    SubCategory sub = subCategoryService.getSubCategoryById(id);
    model.addAttribute("categories", categoryService.getAll());
    model.addAttribute("sub", sub);
    return "admin/sub_category/edit_sub";
  }

  @GetMapping("/admin/sub-category/delete/{id}")
  public String deleteSubCat(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes){
    subCategoryService.delete(id);
    redirectAttributes.addFlashAttribute("message", "Delete SubCategory Successfully");
    redirectAttributes.addFlashAttribute("alertType", "success");
    return "redirect:/admin/sub-category";
  }


}
