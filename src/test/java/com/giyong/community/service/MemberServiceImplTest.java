package com.giyong.community.service;

import com.giyong.community.dto.MemberDto;
import com.giyong.community.entity.Member;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberServiceImplTest {
    @Autowired
    private MemberService memberService;

    @Test
    void addMember() {
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId("test@test.net");
        memberDto.setMemberPw("1234");
        memberDto.setCreatedAt(new Date());
        memberDto.setUpdatedAt(new Date());

        ModelMapper modelMapper = new ModelMapper();
        Member member = modelMapper.map(memberDto, Member.class);
        Member addMember = memberService.addMember(member);
        assertTrue(memberDto.getMemberId().equals(addMember.getMemberId()));
    }

    @Test
    void findMember() {
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId("test@test.net");
        memberDto.setMemberPw("1234");
        memberDto.setCreatedAt(new Date());
        memberDto.setUpdatedAt(new Date());

        ModelMapper modelMapper = new ModelMapper();
        Member member = modelMapper.map(memberDto, Member.class);
        Member addMember = memberService.addMember(member);
        Member findMember = memberService.findMember(addMember.getMno());
        assertTrue(addMember.getMemberId().equals(findMember.getMemberId()));
    }

    @Test
    void modifyMember() {
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId("test@test.net");
        memberDto.setMemberPw("1234");
        memberDto.setCreatedAt(new Date());
        memberDto.setUpdatedAt(new Date());

        ModelMapper modelMapper = new ModelMapper();
        Member member = modelMapper.map(memberDto, Member.class);
        Member addMember = memberService.addMember(member);
        Member findMember = memberService.findMember(addMember.getMno());
        findMember.setNickname("tester");
        Member modifyMember = memberService.modifyMember(findMember);
        assertTrue(modifyMember.getNickname().equals("tester"));
    }

    @Test
    void removeMember() {
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId("test@test.net");
        memberDto.setMemberPw("1234");
        memberDto.setCreatedAt(new Date());
        memberDto.setUpdatedAt(new Date());

        ModelMapper modelMapper = new ModelMapper();
        Member member = modelMapper.map(memberDto, Member.class);
        Member addMember = memberService.addMember(member);
        memberService.removeMember(addMember.getMno());
        Member findMember = memberService.findMember(addMember.getMno());
        assertTrue(findMember == null);
    }
}