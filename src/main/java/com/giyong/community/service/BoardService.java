package com.giyong.community.service;

import com.giyong.community.dto.BoardDto;
import com.giyong.community.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface BoardService {
    Board write(BoardDto boardDto);

    Board modify(BoardDto boardDto);

    Page<Board> findAll(Pageable pageable);

    Page<Board> findAllBySubCategoryId(Long subCategoryId, Pageable pageable);

    Board findById(Long boardId);

    void remove(Long boardId);

    void upViewCount(Long boardId, HttpSession session);

    Long findCommentCount(Long boardId);

    void toggleLike(Long boardId, Long memberId);
}
