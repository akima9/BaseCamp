package com.giyong.community.repository;

import com.giyong.community.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer> {
    @Query(value = "select * from comment where board_id = :boardId", nativeQuery = true)
    List<Comment> findAllByBoardId(@Param("boardId") Integer boardId);
}
