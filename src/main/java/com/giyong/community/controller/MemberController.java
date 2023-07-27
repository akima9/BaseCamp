package com.giyong.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberController {
    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/signUp")
    public String signUp(@RequestParam(value = "memberId", required = false) String memberId, Model model) {
        model.addAttribute("memberId", memberId);
        return "member/signUp";
    }

    @PostMapping("/members")
    public String members(String memberId, String memberPw, String confirmPw, RedirectAttributes redirect) {
        /**
         * TODO
         * 추후 구현 예정
         * 1. 이메일 검증
         * 2. 비밀번호 검증
         * 3. 비밀번호 암호화
         * 4. signUp 페이지로 redirect시 alert 메시지
         */

        // memberPw와 confirmPw가 같은지 확인
        if (memberPw.equals(confirmPw)) { // 같으면, 저장 후 login 페이지로 이동
            return "redirect:/login";
        } else { // 다르면, 다시 signUp 페이지로 이동
            redirect.addAttribute("memberId", memberId);
            return "redirect:/signUp";
        }
    }
}
