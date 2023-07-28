package com.giyong.community.service;

import com.giyong.community.entity.Member;

import java.util.Optional;

public interface MemberService {
    Member create(Member member);

    Optional<Member> findMember(Member member);
}
