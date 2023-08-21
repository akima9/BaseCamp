package com.giyong.community.controller;

import com.giyong.community.dto.CategoryDto;
import com.giyong.community.entity.Category;
import com.giyong.community.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {
    // 항상 admin session 확인 필요
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categorys/list")
    public String list(Model m, @PageableDefault(page = 0, size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Category> categorys = categoryService.findAll(pageable);
        m.addAttribute("categorys", categorys);
        return "admin/category/list";
    }

    @GetMapping("/categorys/create")
    public String create(Model model) {

        return "admin/category/create";
    }

    @PostMapping("/categorys")
    public String create(CategoryDto categoryDto) {
        categoryService.write(categoryDto);
        return "redirect:/categorys/list";
    }
}