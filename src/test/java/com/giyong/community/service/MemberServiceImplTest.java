package com.giyong.community.service;

import com.giyong.community.dto.MemberDto;
import com.giyong.community.entity.Member;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    @BeforeAll
    static void addTempMember(@Autowired MemberService memberService) {
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId("tester");
        memberDto.setMemberPw("1234");
        memberDto.setConfirmPw("1234");
        Member member = memberService.addMember(memberDto);
    }

    @Test
    @Order(1)
    void addMember() {
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId("tester1");
        memberDto.setMemberPw("1234");
        memberDto.setConfirmPw("1234");
        Member member = memberService.addMember(memberDto);
        assertTrue(member.getMemberId().equals(memberDto.getMemberId()));
    }

    @Test
    @Order(2)
    void findMember() {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(1L);
        Member member = memberService.findMember(memberDto);
        assertTrue(member.getId() == memberDto.getId());
    }

    @Test
    @Order(3)
    void modifyMember() {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(2L);
        memberDto.setMemberPw("4321");
        Member member = memberService.modifyMember(memberDto);
        assertTrue(member.getMemberPw().equals(memberDto.getMemberPw()));
    }

    @Test
    @Order(4)
    void removeMember() {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(1L);
        memberService.removeMember(memberDto.getId());
        Member member = memberService.findMember(memberDto);
        assertTrue(member == null);
    }
}