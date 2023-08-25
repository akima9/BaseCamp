package com.giyong.community.repository;

import com.giyong.community.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(value = "select count(*) from comment where board_id = :boardId", nativeQuery = true)
    Long findCommentCountByBoardId(@Param("boardId") Long boardId);
}
