package com.giyong.community.service;

import com.giyong.community.dto.BoardDto;
import com.giyong.community.entity.Board;

import java.util.Optional;

public interface BoardService {
    Board create(BoardDto dto);
    Optional<Board> findById(int boardId);
}
