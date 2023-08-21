package com.giyong.community.controller;

import com.giyong.community.dto.MainCategoryDto;
import com.giyong.community.entity.MainCategory;
import com.giyong.community.service.MainCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/main")
public class MainCategoryController {
    @Autowired
    private MainCategoryService mainCategoryService;
    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/categorys/create")
    public String create() {
        return "admin/mainCategory/create";
    }

    @PostMapping("/categorys")
    public String create(MainCategoryDto mainCategoryDto) {
        System.out.println("MainCategoryController.create");
        System.out.println("mainCategoryDto = " + mainCategoryDto);
        MainCategory mainCategory = mainCategoryService.save(mainCategoryDto);
        System.out.println("mainCategory = " + mainCategory);
        return "redirect:/admin/main/categorys/list";
    }

    @GetMapping("/categorys/list")
    public String list(Model m, @PageableDefault(page = 0, size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<MainCategory> mainCategories = mainCategoryService.findAll(pageable);
        m.addAttribute("mainCategories", mainCategories);
        return "admin/mainCategory/list";
    }

    @GetMapping("/categorys")
    public String categories(Integer mainCategoryId, Integer page, Model m) {
        MainCategory mainCategory = mainCategoryService.findById(mainCategoryId);
        m.addAttribute("mainCategory", mainCategory);
        m.addAttribute("page", page);
        return "admin/mainCategory/modify";
    }

    @PutMapping("/categorys")
    public String modify(MainCategoryDto mainCategoryDto) {
        System.out.println("MainCategoryController.modify");
        mainCategoryService.modify(mainCategoryDto);
        return "redirect:/admin/main/categorys/list";
    }

    @DeleteMapping("/categorys")
    public String delete(MainCategoryDto mainCategoryDto) {
        System.out.println("MainCategoryController.delete");
        mainCategoryService.remove(mainCategoryDto.getMainCategoryId());
        return "redirect:/admin/main/categorys/list";
    }
}
