package com.giyong.community.repository;

import com.giyong.community.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Test
    public void crud() {
        Member member = new Member();
        member.setId("giyong@test.net");
        member.setPassword("1111");
        member.setCreatedAt(new Date());
        member.setUpdatedAt(new Date());

        memberRepository.save(member); //INSERT

        member.setNickname("giyong");
        memberRepository.save(member); //UPDATE

        Optional<Member> findMember = memberRepository.findById(member.getId()); //SELECT

        assertTrue(member.getNickname().equals(findMember.get().getNickname())); //check

        memberRepository.deleteById(member.getId()); //DELETE
        Optional<Member> findMember2 = memberRepository.findById(member.getId()); //SELECT

        assertTrue(findMember2.isEmpty()); //check
    }
}