package com.giyong.community.service;

import com.giyong.community.entity.Member;

import java.util.Optional;

public interface MemberService {
    Member addMember(Member member);

    Member findMember(Integer mno);

    Member modifyMember(Member member);

    void removeMember(Integer mno);
}
