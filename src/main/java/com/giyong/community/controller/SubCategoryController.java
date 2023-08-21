package com.giyong.community.controller;

import com.giyong.community.entity.Category;
import com.giyong.community.entity.SubCategory;
import com.giyong.community.service.CategoryService;
import com.giyong.community.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sub")
public class SubCategoryController {
    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("/categorys/list")
    public String list(Model m, @PageableDefault(page = 0, size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<SubCategory> subCategories = subCategoryService.findAll(pageable);
        m.addAttribute("subCategories", subCategories);
        return "admin/subCategory/list";
    }
}
