package com.aduca.lms.service;

import com.aduca.lms.domain.SubCategory;
import com.aduca.lms.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryService {
  private SubCategoryRepository repo;

  public SubCategoryService(SubCategoryRepository repo) {
    this.repo = repo;
  }

  public SubCategory save(SubCategory cat) {
    return repo.save(cat);
  }

  public List<SubCategory> getAll() {
    return repo.findAll();
  }

  public SubCategory getSubCategoryById(Long id) {
    return repo.findById(id).get();
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }

  public List<SubCategory> findByCategory_Id(Long id){
    return repo.findByCategory_Id(id);
  }
}
