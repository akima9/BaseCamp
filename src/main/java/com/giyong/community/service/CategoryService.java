package com.giyong.community.service;

import com.giyong.community.dto.CategoryDto;
import com.giyong.community.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Category write(CategoryDto categoryDto);

    Category modify(CategoryDto categoryDto);

    Page<Category> findAll(Pageable pageable);

    Category findById(Integer categoryId);

    void remove(Integer categoryId);
}
