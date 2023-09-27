package com.giyong.community.repository;

import com.giyong.community.entity.Board;
import com.giyong.community.entity.LikeBoard;
import com.giyong.community.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeBoard, Long> {
    LikeBoard findByMemberAndBoard(Member member, Board board);
}
