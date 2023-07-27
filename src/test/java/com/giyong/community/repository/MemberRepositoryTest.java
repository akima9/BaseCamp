package com.giyong.community.repository;

import com.giyong.community.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void cleanup() {
        memberRepository.deleteAll();
    }

    @Test
    public void insert() {
        // given
        String memberId = "test@test.com";
        String memberPw = "1234";

        memberRepository.save(Member.builder()
                .id(memberId)
                .password(memberPw)
                .build());
        // when
        List<Member> memberList = memberRepository.findAll();
        // then
        Member member = memberList.get(0);
        Assertions.assertEquals(memberId, member.getId());
        Assertions.assertEquals(memberPw, member.getPassword());
    }
}