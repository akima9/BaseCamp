package com.giyong.community.repository;

import com.giyong.community.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    EntityManagerFactory emf;

    @Test
    public void insert() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Member member = new Member();
        member.setId("giyong@test.net");
        member.setPassword("1111");
        member.setNickname("giyong");
        member.setCreatedAt(new Date());
        member.setUpdatedAt(new Date());

        tx.begin();
        em.persist(member);
        tx.commit();

        Member findMember = em.find(Member.class, "giyong@test.net");
        Assertions.assertEquals(member.getId(), findMember.getId());
    }
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @BeforeEach
//    public void cleanup() {
//        memberRepository.deleteAll();
//    }
//
//    @Test
//    public void insert() {
//        // given
//        String memberId = "test@test.com";
//        String memberPw = "1234";
//
//        memberRepository.save(Member.builder()
//                .id(memberId)
//                .password(memberPw)
//                .build());
//        // when
//        List<Member> memberList = memberRepository.findAll();
//        // then
//        Member member = memberList.get(0);
//        Assertions.assertEquals(memberId, member.getId());
//        Assertions.assertEquals(memberPw, member.getPassword());
//    }
}