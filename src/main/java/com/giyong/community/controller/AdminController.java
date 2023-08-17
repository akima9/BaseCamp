package com.giyong.community.controller;

import com.giyong.community.dto.AdminDto;
import com.giyong.community.entity.Admin;
import com.giyong.community.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(AdminDto adminDto, RedirectAttributes redirect, HttpServletRequest request) {
        System.out.println("adminDto = " + adminDto);
        Admin admin = adminService.findById(adminDto.getAdminId());
        if (admin == null) {
            redirect.addFlashAttribute("resCode", 404);
            return "redirect:/admin/login";
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("adminId", admin.getAdminId());

        return "redirect:/admin";
    }
}
