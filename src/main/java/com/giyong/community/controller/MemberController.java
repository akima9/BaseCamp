package com.giyong.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "member/signUp";
    }
}
