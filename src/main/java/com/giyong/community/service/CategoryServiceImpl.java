package com.giyong.community.service;

import com.giyong.community.dto.CategoryDto;
import com.giyong.community.entity.Category;
import com.giyong.community.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Category write(CategoryDto categoryDto) {
        categoryDto.setCreatedAt(new Date());
        categoryDto.setUpdatedAt(new Date());

        Category category = modelMapper.map(categoryDto, Category.class);

        return categoryRepository.save(category);
    }

    @Override
    public Category modify(CategoryDto categoryDto) {
        Category oldCategory = categoryRepository.findById(categoryDto.getCategoryId()).orElse(null);

        CategoryDto dto = modelMapper.map(oldCategory, CategoryDto.class);
        dto.setCategoryName(categoryDto.getCategoryName());
        dto.setParentCategoryId(categoryDto.getParentCategoryId());
        dto.setUpdatedAt(new Date());

        Category modifyCategory = modelMapper.map(dto, Category.class);
        return categoryRepository.save(modifyCategory);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return (Page<Category>) categoryRepository.findAll(pageable);
    }

    @Override
    public Category findById(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public void remove(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
