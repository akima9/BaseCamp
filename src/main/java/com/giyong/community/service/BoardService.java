package com.giyong.community.service;

import com.giyong.community.dto.BoardDto;
import com.giyong.community.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    Board write(BoardDto boardDto);

    Board modify(BoardDto boardDto);

    List<Board> findAll();

    Board findById(Integer boardId);

    void remove(Integer boardId);
}
