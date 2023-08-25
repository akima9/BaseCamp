package com.giyong.community.service;

import com.giyong.community.dto.MemberDto;
import com.giyong.community.entity.Member;
import com.giyong.community.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public Member addMember(MemberDto memberDto) {
        Member member = modelMapper.map(memberDto, Member.class);
        return memberRepository.save(member);
    }

    @Override
    public Member findMember(MemberDto memberDto) {
        return memberRepository.findById(memberDto.getId()).orElse(null);
    }

    @Override
    public Member findByMemberId(MemberDto memberDto) {
        return memberRepository.findByMemberId(memberDto.getMemberId());
    }

    @Override
    public Member modifyMember(MemberDto memberDto) {
        Member oldMember = memberRepository.findById(memberDto.getId()).orElse(null);

        MemberDto dto = modelMapper.map(oldMember, MemberDto.class);
        dto.setMemberPw(memberDto.getMemberPw());

        Member modifyMember = modelMapper.map(dto, Member.class);

        return memberRepository.save(modifyMember);
    }

    @Override
    public void removeMember(Long id) {
        memberRepository.deleteById(id);
    }
}
