package com.giyong.community.controller;

import com.giyong.community.dto.MemberDto;
import com.giyong.community.entity.Member;
import com.giyong.community.service.MemberService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
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
        PropertyMap<MemberDto, Member> map = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setId(source.getMemberId());
                map().setPassword(source.getMemberPw());
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(map);
        Optional<Member> findedMember = memberService.findMember(modelMapper.map(memberDto, Member.class));

        if (memberDto.getMemberPw().equals(findedMember.get().getPassword())) {
            // 비밀번호가 같으면 로그인 처리
            HttpSession session = request.getSession(true);
            session.setAttribute("memberId", findedMember.get().getId());
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/signUp")
    public String signUp(@RequestParam(value = "memberId", required = false) String memberId, Model model) {
        model.addAttribute("memberId", memberId);
        return "member/signUp";
    }

    @PostMapping("/members")
    public String members(MemberDto memberDto, RedirectAttributes redirect) {
        /**
         * TODO
         * 추후 구현 예정
         * 1. 이메일 검증
         * 2. 비밀번호 검증
         * 3. 비밀번호 암호화
         * 4. signUp 페이지로 redirect시 alert 메시지
         */
        // memberPw와 confirmPw가 같은지 확인
        if (memberDto.getMemberPw().equals(memberDto.getConfirmPw())) {
            // 같으면, 저장 후 login 페이지로 이동
            memberDto.setCreatedAt(new Date());
            PropertyMap<MemberDto, Member> map = new PropertyMap<>() {
                @Override
                protected void configure() {
                    map().setId(source.getMemberId());
                    map().setPassword(source.getMemberPw());
                }
            };
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.addMappings(map);
            Member member = memberService.create(modelMapper.map(memberDto, Member.class));
            return "redirect:/login";
        } else {
            // 다르면, 다시 signUp 페이지로 이동
            redirect.addAttribute("memberId", memberDto.getMemberId());
            return "redirect:/signUp";
        }
    }
}
