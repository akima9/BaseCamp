package com.giyong.community.service;

import com.giyong.community.dto.BoardDto;
import com.giyong.community.entity.Board;
import com.giyong.community.repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Override
    public Board create(BoardDto dto) {
        dto.setCreatedAt(new Date());
        dto.setUpdatedAt(new Date());
        ModelMapper modelMapper = new ModelMapper();
        Board board = modelMapper.map(dto, Board.class);
        return boardRepository.save(board);
    }

    @Override
    public Optional<Board> findById(Integer boardId) {
        return boardRepository.findById(boardId);
    }

    public Iterable<Board> findAll() {
        Iterable<Board> board = boardRepository.findAll();
        return boardRepository.findAll();
    }

    @Override
    public Board modify(BoardDto dto) {
        Optional<Board> board = boardRepository.findById(dto.getBoardId());
        Board findBoard = board.get();
        findBoard.setTitle(dto.getTitle());
        findBoard.setContent(dto.getContent());
        findBoard.setUpdatedAt(new Date());
        return boardRepository.save(findBoard);
    }
}
