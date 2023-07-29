package com.giyong.community.service;

import com.giyong.community.dto.BoardDto;
import com.giyong.community.entity.Board;
import com.giyong.community.repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Override
    public Board create(BoardDto dto) {
        ModelMapper modelMapper = new ModelMapper();
        Board board = modelMapper.map(dto, Board.class);
        return boardRepository.save(board);
    }

    @Override
    public Optional<Board> findById(int boardId) {
        return boardRepository.findById(boardId);
    }
}
