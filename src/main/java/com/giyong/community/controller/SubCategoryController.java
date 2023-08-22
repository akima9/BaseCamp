package com.giyong.community.controller;

import com.giyong.community.dto.SubCategoryDto;
import com.giyong.community.entity.MainCategory;
import com.giyong.community.entity.SubCategory;
import com.giyong.community.service.MainCategoryService;
import com.giyong.community.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/sub")
public class SubCategoryController {
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private MainCategoryService mainCategoryService;

    @GetMapping("/categorys/list")
    public String list(Model m, @PageableDefault(page = 0, size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<SubCategory> subCategories = subCategoryService.findAll(pageable);
        m.addAttribute("subCategories", subCategories);
        return "admin/subCategory/list";
    }

    @GetMapping("/categorys/create")
    public String create(Pageable pageable, Model m) {
        Page<MainCategory> mainCategories = mainCategoryService.findAll(pageable);
        m.addAttribute("mainCategories", mainCategories);
        return "admin/subCategory/create";
    }

    @PostMapping("/categorys")
    public String create(SubCategoryDto subCategoryDto) {
        System.out.println("subCategoryDto = " + subCategoryDto);
        SubCategory subCategory = subCategoryService.save(subCategoryDto);
        System.out.println("subCategory = " + subCategory);
        return "redirect:/admin/sub/categorys/list";
    }

    @GetMapping("/categorys")
    public String categories(Integer subCategoryId, Integer page, Pageable pageable, Model m) {
        SubCategory subCategory = subCategoryService.findById(subCategoryId);
        Page<MainCategory> mainCategories = mainCategoryService.findAll(pageable);
        m.addAttribute("subCategory", subCategory);
        m.addAttribute("page", page);
        m.addAttribute("mainCategories", mainCategories);
        return "admin/subCategory/modify";
    }

    @PutMapping("/categorys")
    public String subCategoryModify(SubCategoryDto subCategoryDto) {
        System.out.println("subCategoryDto = " + subCategoryDto);
        subCategoryService.modify(subCategoryDto);
        return "redirect:/admin/sub/categorys/list";
    }

    @DeleteMapping("/categorys")
    public String subCategoryDelete(SubCategoryDto subCategoryDto) {
        System.out.println("subCategoryDto = " + subCategoryDto);
        subCategoryService.remove(subCategoryDto.getSubCategoryId());
        return "redirect:/admin/sub/categorys/list";
    }
}
