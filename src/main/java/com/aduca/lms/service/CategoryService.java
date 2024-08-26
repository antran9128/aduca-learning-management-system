package com.aduca.lms.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.aduca.lms.domain.Category;
import com.aduca.lms.repository.CategoryRepository;

@Service
public class CategoryService {

    private CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public Category save(Category cat) {
        return repo.save(cat);
    }

    public List<Category> getAll() {
        return repo.findAll(Sort.by(Sort.Direction.ASC, "categoryName"));
    }

    public Category getCategoryById(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Category> getCategoriesLimit(int i) {
      Pageable pageable = PageRequest.of(0, i);
      return repo.findAll(pageable).getContent();
    }
}
