package com.giyong.community.service;

import com.giyong.community.dto.MemberDto;
import com.giyong.community.entity.Member;

import java.util.Optional;

public interface MemberService {
    Member addMember(MemberDto memberDto);

    Member findMember(MemberDto memberDto);

    Member modifyMember(MemberDto memberDto);

    void removeMember(Long id);
}
