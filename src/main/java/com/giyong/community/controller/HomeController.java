package com.giyong.community.controller;

import com.giyong.community.entity.MainCategory;
import com.giyong.community.service.MainCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    private MainCategoryService mainCategoryService;

    @GetMapping("/")
    public String index(Model m) {
        Page<MainCategory> mainCategories = mainCategoryService.findAll(Pageable.unpaged());
        m.addAttribute("mainCategories", mainCategories);
        return "index";
    }

    @GetMapping("/admin")
    public String admin(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        if (session.getAttribute("adminId") == null) {
            return "redirect:/admin/login";
        }

        return "admin";
    }
}
