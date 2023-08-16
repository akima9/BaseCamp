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

    Board findById(Integer boardId);

    void remove(Integer boardId);

    void upViewCount(Integer boardId, HttpSession session);

    Integer findCommentCount(Integer boardId);
}
