package com.giyong.community.service;

import com.giyong.community.entity.Member;

import java.util.Optional;

public interface MemberService {
    Member addMember(Member member);

    Member findMember(Member member);

    Member modifyMember(Member member);

    void removeMember(String memberId);
}
