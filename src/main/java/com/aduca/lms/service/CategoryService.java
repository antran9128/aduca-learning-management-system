package com.aduca.lms.service;

import java.util.List;

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
        return repo.findAll();
    }

    public Category getCategoryById(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
