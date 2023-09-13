package com.giyong.community.controller;

import com.giyong.community.dto.AdminDto;
import com.giyong.community.entity.Admin;
import com.giyong.community.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/add")
    public String addAdmin() {
        return "/admin/add";
    }

    @PutMapping("/edit")
    public String editAdmin(AdminDto adminDto, Integer page, RedirectAttributes redirect) {
        System.out.println("AdminController.editAdmin");
        System.out.println("adminDto = " + adminDto);
        Admin admin = adminService.modify(adminDto);
        redirect.addFlashAttribute("resCode", 200);
        return "redirect:/admin/edit?id="+admin.getId()+"&page="+page;
    }

    @GetMapping("/edit")
    public String goToAdminEdit(AdminDto adminDto, Integer page, Model m) {
        Admin admin = adminService.findById(adminDto.getId());

        m.addAttribute("admin", admin);
        m.addAttribute("page", page);

        return "admin/edit";
    }

    @GetMapping("/list")
    public String getAdminList(
            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable,
            Model m
    ) {
        Page<Admin> admins = adminService.findAll(pageable);
        m.addAttribute("admins", admins);
        return "admin/list";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.invalidate();
        return "redirect:/admin";
    }

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(AdminDto adminDto, RedirectAttributes redirect, HttpServletRequest request) {
        Admin admin = adminService.findByAdminId(adminDto.getAdminId());

        if (admin == null) {
            redirect.addFlashAttribute("resCode", 404);
            return "redirect:/admin/login";
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("adminId", admin.getId());

        return "redirect:/admin";
    }
}
