package com.giyong.community.controller;

import com.giyong.community.dto.MemberDto;
import com.giyong.community.entity.Member;
import com.giyong.community.service.MemberService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Optional;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(MemberDto memberDto, RedirectAttributes redirect, HttpServletRequest request) {
//        ModelMapper modelMapper = new ModelMapper();
//        Member findMember = memberService.findMember(modelMapper.map(memberDto, Member.class));
//
//        if (findMember == null) {
//            redirect.addFlashAttribute("resCode", 404);
//            return "redirect:/login";
//        }
//
//        if (passwordEncoder.matches(memberDto.getMemberPw(), findMember.getMemberPw())) {
//            // 비밀번호가 같으면 로그인 처리
//            HttpSession session = request.getSession(true);
//            session.setAttribute("memberId", findMember.getMemberId());
//            return "redirect:/";
//        } else {
//            redirect.addFlashAttribute("resCode", 405);
//            return "redirect:/login";
//        }
        return "";
    }

    @GetMapping("/signUp")
    public String signUp(@RequestParam(value = "memberId", required = false) String memberId, Model model) {
        model.addAttribute("memberId", memberId);
        return "member/signUp";
    }

    @PostMapping("/members")
    public String members(MemberDto memberDto, RedirectAttributes redirect) {
//        ModelMapper modelMapper = new ModelMapper();
//        Member newMember = modelMapper.map(memberDto, Member.class);
//        Member findMember = memberService.findMember(newMember);
//
//        if (findMember != null) {
//            redirect.addFlashAttribute("resCode", 300);
//            return "redirect:/signUp";
//        }
//
//        // memberPw와 confirmPw가 같은지 확인
//        if (memberDto.getMemberPw().equals(memberDto.getConfirmPw())) {
//            // 같으면, 저장 후 login 페이지로 이동
//            memberDto.setCreatedAt(new Date());
//            memberDto.setMemberPw(passwordEncoder.encode(memberDto.getMemberPw()));
//            Member member = memberService.addMember(modelMapper.map(memberDto, Member.class));
//
//            if (member != null) {
//                redirect.addFlashAttribute("resCode", 200);
//            } else {
//                redirect.addFlashAttribute("resCode", 500);
//                return "redirect:/signUp";
//            }
//            return "redirect:/login";
//        } else {
//            // 다르면, 다시 signUp 페이지로 이동
//            redirect.addAttribute("memberId", memberDto.getMemberId());
//            return "redirect:/signUp";
//        }
        return "";
    }
}
