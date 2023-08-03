package com.giyong.community.service;

import com.giyong.community.dto.MemberDto;
import com.giyong.community.entity.Member;
import com.giyong.community.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member findMember(Member member) {
        return memberRepository.findById(member.getMemberId()).orElse(null);
    }

    @Override
    public Member modifyMember(Member member) {
        Member oldMember = memberRepository.findById(member.getMemberId()).orElse(null);

        ModelMapper modelMapper = new ModelMapper();

        MemberDto dto = modelMapper.map(member, MemberDto.class);
        dto.setMemberPw(member.getMemberPw());
        dto.setNickname(member.getNickname());
        dto.setUpdatedAt(new Date());

        Member modifyMember = modelMapper.map(dto, Member.class);

        return memberRepository.save(modifyMember);
    }

    @Override
    public void removeMember(String memberId) {
        memberRepository.deleteById(memberId);
    }
}
