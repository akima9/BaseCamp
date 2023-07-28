package com.giyong.community.service;

import com.giyong.community.entity.Member;
import com.giyong.community.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public Member create(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Optional<Member> findMember(Member member) {
        return memberRepository.findById(member.getId());
    }
}
