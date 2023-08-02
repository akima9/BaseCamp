package com.giyong.community.repository;

import com.giyong.community.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Integer> {
}
