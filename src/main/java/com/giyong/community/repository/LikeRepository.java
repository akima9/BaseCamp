package com.giyong.community.repository;

import com.giyong.community.entity.Board;
import com.giyong.community.entity.Like;
import com.giyong.community.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findByMemberAndBoard(Member member, Board board);
}
