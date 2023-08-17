package com.giyong.community.service;

import com.giyong.community.dto.CategoryDto;
import com.giyong.community.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CategoryServiceImplTest {
    @Autowired
    private CategoryService categoryService;

    @Test
    void write() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setParentCategoryId(1);
        categoryDto.setCategoryName("자유게시판");

        Category category = categoryService.write(categoryDto);
        assertTrue(categoryDto.getParentCategoryId() == category.getCategoryId());
        assertTrue(categoryDto.getCategoryName().equals(category.getCategoryName()));
    }

    @Test
    void modify() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setParentCategoryId(1);
        categoryDto.setCategoryName("자유게시판");

        Category category = categoryService.write(categoryDto);
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryName("익명게시판");

        Category modifyCategory = categoryService.modify(categoryDto);
        assertTrue(category.getCategoryId() == modifyCategory.getCategoryId());
        assertTrue(!category.getCategoryName().equals(modifyCategory.getCategoryName()));
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setParentCategoryId(1);
        categoryDto.setCategoryName("자유게시판");

        Category category = categoryService.write(categoryDto);
        Category findCategory = categoryService.findById(1);
        assertTrue(category.getCategoryName().equals(findCategory.getCategoryName()));
    }

    @Test
    void remove() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setParentCategoryId(1);
        categoryDto.setCategoryName("자유게시판");

        Category category = categoryService.write(categoryDto);
        categoryService.remove(1);
        Category findCategory = categoryService.findById(1);
        assertTrue(findCategory == null);
    }
}